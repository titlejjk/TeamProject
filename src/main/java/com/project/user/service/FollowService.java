package com.project.user.service;

import com.project.user.dto.FollowDto;

import java.util.List;


public interface FollowService {

    //팔로우 언팔로우 토글 메서드
    String toggleFollow(FollowDto followDto);

    //특정 회원의 팔로워 조회
    List<FollowDto> findFollowers(String userEmail);

    //특정 회원의 팔로잉 조회
    List<FollowDto> findFollowings(String userEmail);

    //특정 회원의 팔로워 갯수 조회
    int countFollowers(String userEmail);

    //특정 회원의 팔로잉 갯수 조회
    int countFollowings(String userEmail);

}
