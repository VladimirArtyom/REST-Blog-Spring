package io.xor.restapiblog.service.implementation;

import io.xor.restapiblog.entity.Post;
import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.repository.PostRepository;
import io.xor.restapiblog.service.contract.PostService;
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
       Post post = this.postRepository.findById(id).orElseThrow(() -> new RuntimeException("Bro"));
       return mapToDTO(post);
    }

    @Override
    public List<PostDTO> getPosts() {
        List<Post> posts = this.postRepository.findAll();
        return posts.stream().map((post) -> mapToDTO(post)).toList();
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        Post post = this.postRepository.save(mapToPost(postDto));
        return mapToDTO(post);
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
