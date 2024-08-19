package io.xor.restapiblog.service.contract;

import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.repository.PostRepository;

import java.util.List;

public interface PostService {
    PostDTO getPost(long id);
    List<PostDTO> getPosts();
    PostDTO createPost(PostDTO post);
}
