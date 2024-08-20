package io.xor.restapiblog.service.contract;

import io.xor.restapiblog.entity.Post;
import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.payload.PostResponse;
import io.xor.restapiblog.repository.PostRepository;

import java.util.List;

public interface PostService {
    PostDTO getPost(long id);
    PostResponse getPosts(int pageNo, int pageSize);
    PostDTO createPost(PostDTO post);
    PostDTO updatePost(long id, PostDTO newPost);
    void deletePost(long id);
}
