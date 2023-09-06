package com.project.party_post.post.controller;

import com.project.party_post.post.Service.PostService;
import com.project.party_post.post.dto.PostDto;
import com.project.party_post.post.dto.PostImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 생성메서드
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDto postDto) {
        postService.insertPost(postDto);
        return ResponseEntity.ok().build();
    }

    //모든 게시글 목록을 조회하는 메서드
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.selectAllPosts());
    }

    // 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable int postId) {
        PostDto postDto = postService.selectPostById(postId);
        postService.incrementViewCount(postId); // 조회수 증가
        return ResponseEntity.ok(postDto);
    }

    //게시글 수정 메서드
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return ResponseEntity.ok().build();
    }

    //게시글 삭제 메서드
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

}
