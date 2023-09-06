package com.project.party_post.post_like.service;

import com.project.party_post.post_like.dto.LikesDto;

public interface LikesService {

    //좋아요 insert/delete
    String toggleLike(LikesDto likesDto);
    //게시글 하나의 좋아요 갯수 조회
    int countLikesByPostId(int postId);
    //
    boolean isLikedByUser(LikesDto likesDto);

}
