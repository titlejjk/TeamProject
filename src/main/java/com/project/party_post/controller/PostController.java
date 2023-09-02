package com.project.party_post.controller;

import com.project.party_post.Service.PostService;
import com.project.party_post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{post_id}")
    public PostDto getPostById(@PathVariable int postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{post_id}")
    public void updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
        postService.updatePost(postId, postDto);
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
    }
}
