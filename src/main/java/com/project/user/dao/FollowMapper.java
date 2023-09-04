package com.project.user.dao;

import com.project.party_post.post_like.dto.LikeDto;
import com.project.user.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {
    void insertFollow(FollowDto followDto);
    void deleteFollow(FollowDto followDto);
    int countFollow(FollowDto followDto);
    int countFollowers(String userEmail);
    int countFollowings(String userEmail);
    boolean isFollowing(String followerEmail, String followingEmail);
}
