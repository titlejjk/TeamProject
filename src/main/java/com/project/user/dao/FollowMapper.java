package com.project.user.dao;

import com.project.user.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {

    //Follow add
    void insertFollow(FollowDto followDto);

    //Select Follower
    List<FollowDto> findFollowers(String userEmail);

    //Select Following
    List<FollowDto> findFollowings(String userEmail);

    //Delete Follow
    void deleteFollow(FollowDto followDto);

    //팔로우 관계의 존재 여부를 확인
    int countFollow(@Param("followerEmail") String followerEmail, @Param("followingEmail") String followingEmail);

    //팔로워 갯수 조회
    int countFollowers(String userEmail);

    //팔로잉 갯수 조회
    int countFollowings(String userEmail);
}
