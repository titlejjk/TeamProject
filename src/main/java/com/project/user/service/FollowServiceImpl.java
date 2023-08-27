package com.project.user.service;

import com.project.user.dao.FollowMapper;
import com.project.user.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    //FollowMapper 의존성 주입
    private final FollowMapper followMapper;

    //팔로우/언팔로우 토글
    @Override
    public String toggleFollow(FollowDto followDto) {
        int count = followMapper.countFollow(followDto.getFollowerEmail(), followDto.getFollowingEmail());

        if(count > 0){
            followMapper.deleteFollow(followDto);
            return "언팔로우 되었습니다.";
        }else{
            followMapper.insertFollow(followDto);
            return "팔로우 되었습니다.";
        }
    }


}
