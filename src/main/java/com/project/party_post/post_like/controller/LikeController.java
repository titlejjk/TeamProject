package com.project.party_post.post_like.controller;

import com.project.party_post.post_like.dto.LikeDto;
import com.project.party_post.post_like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    //의존성 주입
    private final LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikeDto likeDto) {
        String message = likeService.toggleLike(likeDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countLikesByPostId(@PathVariable int postId) {
        int count = likeService.countLikesByPostId(postId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/isLiked/{userNum}/{postId}")
    public ResponseEntity<Boolean> isLikedByUser(@PathVariable int userNum, @PathVariable int postId) {
        boolean isLiked = likeService.isLikedByUser(userNum, postId);
        return ResponseEntity.ok(isLiked);
    }
}
