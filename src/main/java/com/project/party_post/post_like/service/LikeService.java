package com.project.party_post.post_like.service;

import com.project.party_post.post_like.dto.LikeDto;

public interface LikeService {

    String toggleLike(LikeDto likeDto);
    int countLikesByPostId(int postId);
    boolean isLikedByUser(int userNum, int postId);

}
