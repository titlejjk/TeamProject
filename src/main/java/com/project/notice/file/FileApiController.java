package com.project.notice.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class FileApiController {

    private final FileService fileService;
    private final FileUtils fileUtils;

    // 파일 리스트 조회
    @GetMapping("/file/{noticeId}")
    public List<FileDto> findAllFileByPostId(@PathVariable final Long noticeId) {
        return fileService.findAllFileByNoticeId(noticeId);
    }


    // 첨부파일 다운로드
    // test 주석 추가
    @GetMapping("/file/{noticeId}/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable final Long noticeId, @PathVariable final Long fileId) {

        FileDto file = fileService.findFileById(fileId);
        Resource resource = fileUtils.readFileAsResource(file);
        try {
            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
        }

    }

}
