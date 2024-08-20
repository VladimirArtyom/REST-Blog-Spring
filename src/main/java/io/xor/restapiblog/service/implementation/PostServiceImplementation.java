package io.xor.restapiblog.service.implementation;

import io.xor.restapiblog.entity.Post;
import io.xor.restapiblog.exception.ResourceNotFoundException;
import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.payload.PostResponse;
import io.xor.restapiblog.repository.PostRepository;
import io.xor.restapiblog.service.contract.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;

    PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO getPost(long id) {
       Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
       return mapToDTO(post);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = this.postRepository.findAll(pageable);
        List<PostDTO> postList = posts.getContent().stream().map((d) -> mapToDTO(d)).toList();
        PostResponse postResponse = mapToPostResponse(posts);
        return postResponse;
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        Post post = this.postRepository.save(mapToPost(postDto));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(long id, PostDTO newPost) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

        post.setTitle(newPost.getTitle() == null ? post.getTitle() : newPost.getTitle());
        post.setDescription(newPost.getDescription() == null ? post.getDescription() : newPost.getDescription());
        post.setContent(newPost.getContent() == null ? post.getContent() : newPost.getContent());

        this.postRepository.save(post);
        return mapToDTO(post);
    }

    @Override
    public void deletePost(long id) {
        Post post = this.postRepository.findById(id).orElseThrow(()-> new RuntimeException("Bro"));
        this.postRepository.deleteById(post.getId());
    }

    private <T extends Page> PostResponse mapToPostResponse(T posts) {
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(posts.getContent());
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    private PostDTO mapToDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;
    }

    private Post mapToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        return post;
    }

}
