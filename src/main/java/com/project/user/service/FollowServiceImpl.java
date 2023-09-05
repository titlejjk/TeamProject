package com.project.user.service;

import com.project.party_post.post_like.dto.LikeDto;
import com.project.user.dao.FollowMapper;
import com.project.user.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowMapper followMapper;

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

    @Override
    public int countFollowers(String userEmail) {
        return followMapper.countFollowers(userEmail);
    }

    @Override
    public int countFollowings(String userEmail) {
        return followMapper.countFollowings(userEmail);
    }

    @Override
    public boolean isFollowing(String followerEmail, String followingEmail) {
        return followMapper.isFollowing(followerEmail, followingEmail);
    }
}
