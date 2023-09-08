package com.project.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 저장
     */
    @Transactional
    public Long savePost(final NoticeDto params) {
        noticeMapper.save(params);
        return params.getId();
    }

    /**
     * 공지사항 상세정보 조회
     */
    public NoticeDto findPostById(final Long id) {
        return noticeMapper.findById(id);
    }

    /**
     * 공지사항 수정
     */
    @Transactional
    public Long updatePost(final NoticeDto params) {
        noticeMapper.update(params);
        return params.getId();
    }

    /**
     * 공지사항 삭제
     */
    public Long deletePost(final Long id) {
        noticeMapper.deleteById(id);
        return id;
    }

    /**
     * 공지사항 리스트 조회
     */
    public List<NoticeDto> findAllPost() {
        return noticeMapper.findAll();
    }

}
