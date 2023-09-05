package com.project.user.service;

import com.project.party_post.post_like.dto.LikeDto;
import com.project.user.dto.FollowDto;

import java.util.List;


public interface FollowService {

    String toggleFollow(FollowDto followDto);
    int countFollowers(String userEmail);
    int countFollowings(String userEmail);
    boolean isFollowing(String followerEmail, String followingEmail);
}
