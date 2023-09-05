package com.project.party_post.comment.controller;

import com.project.party_post.comment.dto.CommentDto;
import com.project.party_post.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.insertComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestParam int commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable int commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable int postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
}
