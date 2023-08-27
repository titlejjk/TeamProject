package com.project.user.service;

import com.project.user.dto.FollowDto;


public interface FollowService {

    //팔로우 언팔로우 토글 메서드
    public String toggleFollow(FollowDto followDto);
}
