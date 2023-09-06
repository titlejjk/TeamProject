package com.project.notice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeDto {
    private Long id;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private LocalDateTime createdDate;     // 생성일시
    private List<MultipartFile> files = new ArrayList<>();    // 첨부파일 List
    private List<Long> removeFileIds = new ArrayList<>(); // 삭제할 첨부파일 id List
}
