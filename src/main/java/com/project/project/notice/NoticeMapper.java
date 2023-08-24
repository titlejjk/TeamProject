package com.project.project.notice;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {


    /**
     * 공지사항 저장
     */
    void save(NoticeDto params);

    /**
     * 공지사항 상세정보 조회
     */
    NoticeDto findById(Long id);

    /**
     * 공지사항 수정
     */
    void update(NoticeDto params);

    /**
     * 공지사항 삭제
     */
    void deleteById(Long id);

    /**
     * 공지사항 리스트 조회
     */
    List<NoticeDto> findAll();

}
