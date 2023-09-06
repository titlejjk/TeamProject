package com.project.party_post.post_like.controller;

import com.project.party_post.post_like.dto.LikesDto;
import com.project.party_post.post_like.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    //의존성 주입
    private final LikesService likesService;

    //좋아요 insert/delete
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikesDto likesDto) {
        String message = likesService.toggleLike(likesDto);
        return ResponseEntity.ok(message);
    }

    //하나의 게시물의 좋아요 갯수 조회
    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countLikesByPostId(@PathVariable int postId) {
        int count = likesService.countLikesByPostId(postId);
        return ResponseEntity.ok(count);
    }

    //좋아요 중복 방지
    @GetMapping("/isLiked")
    public ResponseEntity<Boolean> isLikedByUser(@RequestBody LikesDto likesDto) {
        boolean isLiked = likesService.isLikedByUser(likesDto);
        return ResponseEntity.ok(isLiked);
    }
}
