package com.project.party_post.post_like.service;

import com.project.party_post.post_like.dao.LikesMapper;
import com.project.party_post.post_like.dto.LikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    //의존성 주입
    private final LikesMapper likesMapper;


    @Override
    public String toggleLike(LikesDto likesDto) {
        if (likesMapper.isLikedByUser(likesDto)) {
            likesMapper.deleteLikeByUserAndPost(likesDto);
            return "Unliked successfully";
        } else {
            likesMapper.insertLike(likesDto);
            return "Liked successfully";
        }
    }

    @Override
    public int countLikesByPostId(int postId) {
        return likesMapper.countLikesByPostId(postId);
    }

    @Override
    public boolean isLikedByUser(LikesDto likesDto) {
        return likesMapper.isLikedByUser(likesDto);
    }
}
