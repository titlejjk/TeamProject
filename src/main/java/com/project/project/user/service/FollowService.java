package com.project.project.user.service;

import com.project.project.user.dao.FollowMapper;
import com.project.project.user.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface FollowService {

    //사용자 팔로우
    void followUser(FollowDto followDto);

    //팔로우 목록 조회
    List<FollowDto> getFollowers(String userEmail);

    //팔로잉 목록 조회
    List<FollowDto> getFollowings(String userEmail);

    //사용자 언팔로우
    void unfollowUser(FollowDto followDto);
}
