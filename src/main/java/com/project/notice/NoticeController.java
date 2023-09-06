package com.project.notice;

import com.project.notice.file.FileDto;
import com.project.notice.file.FileService;
import com.project.notice.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {


    private final NoticeService noticeService;
    private final FileService fileService;
    private final FileUtils fileUtils;
    // 게시글 생성
    @PostMapping("/insert") // POST
    public ResponseEntity<Long> savePost(@ModelAttribute NoticeDto params) {
        Long id = noticeService.savePost(params);
        List<FileDto> files = fileUtils.uploadFiles(params.getFiles());
        fileService.saveFiles(id, files);
        return ResponseEntity.ok(id); // 생성된 게시글 ID 반환
    }
    // 모든 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto>> getPostList() {
        List<NoticeDto> posts = noticeService.findAllPost();
        return ResponseEntity.ok(posts);
    }
    // 특정 ID의 게시글 조회
    @GetMapping("/list/{id}")
    public ResponseEntity<NoticeDto> getPostById(@PathVariable Long id) {
        NoticeDto post = noticeService.findPostById(id);
        System.out.println(post.getCreatedDate());
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Void> updatePost(@ModelAttribute NoticeDto noticeDto) {
        // 1. 게시글 정보 수정
        noticeService.updatePost(noticeDto);
        // 2. 파일 업로드 (to disk)
        List<FileDto> uploadFiles = fileUtils.uploadFiles(noticeDto.getFiles());
        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(noticeDto.getId(), uploadFiles);
        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileDto> deleteFiles = fileService.findAllFileByIds(noticeDto.getRemoveFileIds());
        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);
        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(noticeDto.getRemoveFileIds());
        return ResponseEntity.ok().build();
    }
    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,@ModelAttribute NoticeDto noticeDto) {
        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileDto> deleteFiles = fileService.findAllFileByIds(noticeDto.getRemoveFileIds());
        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);
        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(noticeDto.getRemoveFileIds());
        noticeService.deletePost(id);
        return ResponseEntity.ok().build();
    }

}
