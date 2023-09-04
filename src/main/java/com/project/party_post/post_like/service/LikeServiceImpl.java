package com.project.party_post.post_like.service;

import com.project.party_post.post_like.dao.LikeMapper;
import com.project.party_post.post_like.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    //의존성 주입
    private final LikeMapper likeMapper;


    @Override
    public String toggleLike(LikeDto likeDto) {
        if (likeMapper.isLikedByUser(likeDto.getUserNum(), likeDto.getPostId())) {
            likeMapper.deleteLikeByUserAndPost(likeDto.getUserNum(), likeDto.getPostId());
            return "Unliked successfully";
        } else {
            likeMapper.insertLike(likeDto);
            return "Liked successfully";
        }
    }

    @Override
    public int countLikesByPostId(int postId) {
        return likeMapper.countLikesByPostId(postId);
    }

    @Override
    public boolean isLikedByUser(int userNum, int postId) {
        return likeMapper.isLikedByUser(userNum, postId);
    }
}
