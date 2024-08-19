package io.xor.restapiblog.controller;

import io.xor.restapiblog.payload.PostDTO;
import io.xor.restapiblog.service.contract.PostService;
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
    ResponseEntity<List<PostDTO>>getPosts() {
        return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(this.postService.getPost(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PostDTO> createPost(@RequestBody(required = true) PostDTO postDTO) {
        return new ResponseEntity<>(this.postService.createPost(postDTO), HttpStatus.CREATED);
    }


}
