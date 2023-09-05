package com.project.party_post.comment.dao;

import com.project.party_post.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //댓글 생성
    void insertComment(CommentDto commentDto);

    //댓글 수정
    void updateComment(CommentDto commentDto);

    //댓글 삭제
    void deleteComment(int commentId);

    //특정회원의 댓글을 조회
    CommentDto selectCommentById(int commentId);

    //게시글의 댓글 조회
    List<CommentDto> selectCommentsByPostId(int postId);
}
