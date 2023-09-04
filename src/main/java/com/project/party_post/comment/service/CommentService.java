package com.project.party_post.comment.service;

import com.project.party_post.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    //댓글 생성
    void insertComment(CommentDto commentDto);
    //댓글 수정
    void updateComment(CommentDto commentDto);
    //댓글 삭제
    void deleteComment(int commentId);
    //특정회원의 댓글 조회
    CommentDto getCommentById(int commentId);
    //하나의 게시물의 대한 댓글 조회
    List<CommentDto> getCommentsByPostId(int postId);
}
