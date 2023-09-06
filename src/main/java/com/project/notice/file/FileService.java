package com.project.notice.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    @Transactional
    public void saveFiles(final Long noticeId, final List<FileDto> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileDto file : files) {
            file.setNoticeId(noticeId);
        }
        fileMapper.saveAll(files);
    }

    /**
     * 파일 리스트 조회
     */
    public List<FileDto> findAllFileByNoticeId(final Long noticeId) {
        return fileMapper.findAllByNoticeId(noticeId);
    }

    /**
     * 파일 리스트 조회
     */
    public List<FileDto> findAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return fileMapper.findAllByIds(ids);
    }

    /**
     * 파일 삭제 (from Database)
     */
    @Transactional
    public void deleteAllFileByIds(final List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        fileMapper.deleteAllByIds(ids);
    }


    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    public FileDto findFileById(final Long id) {
        return fileMapper.findById(id);
    }

}
