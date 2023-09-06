package com.project.noticecomment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeCommentController {


    private final NoticeCommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/api/comments/{postId}")
    public NoticeCommentDto saveComment(@PathVariable final Long postId, @RequestBody final NoticeCommentDto params) {
        params.setPostId(postId);
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }
    // 댓글 리스트 조회
    @GetMapping("/api/comments/{postId}")
    public List<NoticeCommentDto> findAllComment(@PathVariable final Long postId) {
        return commentService.findAllComment(postId);
    }
    // 댓글 상세정보 조회
    @GetMapping("/api/{postId}/comments/{id}")
    public NoticeCommentDto findCommentById(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.findCommentById(id);
    }


    // 기존 댓글 수정
    @PatchMapping("/api/{postId}/comments/{id}")
    public NoticeCommentDto updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final NoticeCommentDto params) {
        params.setPostId(postId);
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }
    // 댓글 삭제
    @DeleteMapping("/api/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.deleteComment(id);
    }

}
