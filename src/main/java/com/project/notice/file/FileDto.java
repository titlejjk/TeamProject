package com.project.notice.file;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDto {
    private Long id;                      // 파일 번호 (PK)
    private Long noticeId;                  // 게시글 번호 (FK)
    private String originalName;          // 원본 파일명
    private String saveName;              // 저장 파일명
    private long size;                    // 파일 크기
    private Boolean deleteYn;             // 삭제 여부
    private LocalDateTime createdDate;    // 생성일시
    private LocalDateTime deletedDate;    // 삭제일시


    @Builder
    public FileDto(String originalName, String saveName, long size) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }


}
