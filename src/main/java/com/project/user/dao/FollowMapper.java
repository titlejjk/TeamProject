package com.project.user.dao;

import com.project.user.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;

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

    //팔로우 관계의 존재 여부를 확인 (이름을 명시적으로 지정해주기 위해 @Param사용)
    int countFollow(FollowDto followDto);


    //팔로워 갯수 조회
    int countFollowers(String userEmail);

    //팔로잉 갯수 조회
    int countFollowings(String userEmail);
}
