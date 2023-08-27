package com.project.noticecomment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeCommentMapper {

    /**
     * 댓글 저장
     */
    void save(NoticeCommentDto params);

    /**
     * 댓글 상세정보 조회
     */
    NoticeCommentDto findById(Long id);

    /**
     * 댓글 수정
     */
    void update(NoticeCommentDto params);

    /**
     * 댓글 삭제
     */
    void deleteById(Long id);

    /**
     * 댓글 리스트 조회
     */
    List<NoticeCommentDto> findAll(Long postId);

}
