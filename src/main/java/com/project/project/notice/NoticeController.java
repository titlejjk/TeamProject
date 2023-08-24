package com.project.project.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class NoticeController {


    private final NoticeService noticeService;
    // 게시글 생성
    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody NoticeDto params) {
        noticeService.savePost(params);
        return ResponseEntity.ok().build();
    }
    // 모든 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<NoticeDto>> getPostList() {
        List<NoticeDto> posts = noticeService.findAllPost();
        return ResponseEntity.ok(posts);
    }
    // 특정 ID의 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<NoticeDto> getPostById(@PathVariable Long id) {
        NoticeDto post = noticeService.findPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody NoticeDto params) {
        params.setId(id);
        noticeService.updatePost(params);
        return ResponseEntity.ok().build();
    }
    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        noticeService.deletePost(id);
        return ResponseEntity.ok().build();
    }

}
