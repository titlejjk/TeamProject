package com.project.user.dao;

import com.project.user.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
    void insertFollow(FollowDto followDto);
    void deleteFollow(FollowDto followDto);
    int countFollow(FollowDto followDto);
    int countFollowers(String userEmail);
    int countFollowings(String userEmail);
    boolean isFollowing(String followerEmail, String followingEmail);
}
