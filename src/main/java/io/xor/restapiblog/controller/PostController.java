package io.xor.restapiblog.controller;

import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.payload.PostResponse;
import io.xor.restapiblog.service.contract.PostService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    ResponseEntity<PostResponse>getPosts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = true) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = true) int pagSize
    ) {
        return new ResponseEntity<>(this.postService.getPosts(pageNo, pagSize),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(this.postService.getPost(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PostDTO> createPost(@RequestBody(required = true) PostDTO postDTO) {
        return new ResponseEntity<>(this.postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deletePostById(@PathVariable(name="id") long id) {
        this.postService.deletePost(id);
        return new ResponseEntity<>("Post deleted sucessfully", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<PostDTO> updatePost(@PathVariable(name = "id") long id,
                                       @RequestBody PostDTO postDTO) {
        PostDTO postDtoUpdated = this.postService.updatePost(id, postDTO);
        return new ResponseEntity<>(postDtoUpdated, HttpStatus.OK);
    }

}
