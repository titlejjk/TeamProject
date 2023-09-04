package com.project.user.controller;

import com.project.party_post.post_like.dto.LikeDto;
import com.project.user.dto.FollowDto;
import com.project.user.service.FollowServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowServiceImpl followService;

    // 좋아요 토글
    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody FollowDto followDto) {
        String message = followService.toggleFollow(followDto);
        return ResponseEntity.ok(message);
    }

    // 특정 게시글에 대한 좋아요 갯수 조회
    @GetMapping("/followers/count/{userEmail}")
    public ResponseEntity<Integer> countLikesByPostId(@PathVariable String userEmail) {
        int count = followService.countFollowers(userEmail);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/followings/count/{userEmail}")
    public ResponseEntity<Integer> countFollowings(@PathVariable String userEmail) {
        int count = followService.countFollowings(userEmail);
        return ResponseEntity.ok(count);
    }
    // 현재 사용자가 특정 회원을 팔로우했는지 확인
    @GetMapping("/isFollowing/{followerEmail}/{followingEmail}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable String followerEmail, @PathVariable String followingEmail) {
        boolean isFollowing = followService.isFollowing(followerEmail, followingEmail);
        return ResponseEntity.ok(isFollowing);
    }
}
