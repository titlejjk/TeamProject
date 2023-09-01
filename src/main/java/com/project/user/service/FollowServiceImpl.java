package com.project.user.service;

import com.project.user.dao.FollowMapper;
import com.project.user.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    //FollowMapper 의존성 주입
    private final FollowMapper followMapper;

    //팔로우/언팔로우 토글
    @Override
    public String toggleFollow(FollowDto followDto) {
        int count = followMapper.countFollow(followDto);

        if (count > 0) {
            followMapper.deleteFollow(followDto);
            return "Unfollowed successfully";
        } else {
            followMapper.insertFollow(followDto);
            return "Followed successfully";
        }
    }
    //특정 회원의 팔로워 조회
    @Override
    public List<FollowDto> findFollowers(String userEmail) {
        return followMapper.findFollowers(userEmail);
    }
    //특정 회원의 팔로잉 조회
    @Override
    public List<FollowDto> findFollowings(String userEmail) {
        return followMapper.findFollowings(userEmail);
    }
    //특정 회원의 팔로워 갯수 조회
    @Override
    public int countFollowers(String userEmail) {
        return followMapper.countFollowers(userEmail);
    }
    //특정 회원의 팔로잉 갯수 조회
    @Override
    public int countFollowings(String userEmail) {
        return followMapper.countFollowings(userEmail);
    }


}
