package com.project.user.service;

import com.project.user.dto.FollowDto;


public interface FollowService {

    String toggleFollow(FollowDto followDto);
    int countFollowers(String userEmail);
    int countFollowings(String userEmail);
    boolean isFollowing(String followerEmail, String followingEmail);
}
