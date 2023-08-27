package com.project.noticecomment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeCommentService {


    private final NoticeCommentMapper commentMapper;

    /**
     * 댓글 저장
     */
    @Transactional
    public Long saveComment(final NoticeCommentDto params) {
        commentMapper.save(params);
        return params.getId();
    }

    /**
     * 댓글 상세정보 조회
     */
    public NoticeCommentDto findCommentById(final Long id) {
        return commentMapper.findById(id);
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public Long updateComment(final NoticeCommentDto params) {
        commentMapper.update(params);
        return params.getId();
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public Long deleteComment(final Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     */
    public List<NoticeCommentDto> findAllComment(final Long postId) {
        return commentMapper.findAll(postId);
    }

}
