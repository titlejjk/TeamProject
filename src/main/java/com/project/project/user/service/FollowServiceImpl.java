package com.project.project.user.service;

import com.project.project.user.dao.FollowMapper;
import com.project.project.user.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    //FollowMapper 의존성 주입
    private final FollowMapper followMapper;

    //사용자 팔로우
    @Override
    public void followUser(FollowDto followDto) {
        followMapper.insertFollow(followDto);
    }

    //팔로워 목록 조회
    @Override
    public List<FollowDto> getFollowers(String userEmail) {
        return followMapper.findFollowers(userEmail);
    }

    //팔로잉 목록 조회
    @Override
    public List<FollowDto> getFollowings(String userEmail) {
        return followMapper.findFollowings(userEmail);
    }

    //사용자 언팔로우
    @Override
    public void unfollowUser(FollowDto followDto) {
        followMapper.deleteFollow(followDto);
    }
}
