package com.project.notice.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    void saveAll(List<FileDto> files);
    /**
     * 파일 리스트 조회
     */
    List<FileDto> findAllByNoticeId(Long noticeId);

    /**
     * 파일 리스트 조회
     */
    List<FileDto> findAllByIds(List<Long> ids);

    /**
     * 파일 수정 삭제
     * @param ids - PK 리스트
     */
    void deleteAllByIds(List<Long> ids);

    /**
     * 그만해 이러다 다죽어
     */


    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    FileDto findById(Long id);

}
