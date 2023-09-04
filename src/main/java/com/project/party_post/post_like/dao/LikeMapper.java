package com.project.party_post.post_like.dao;

import com.project.party_post.post_like.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

    void insertLike(LikeDto likeDto);
    void deleteLikeByUserAndPost(int userNum, int postId);
    int countLikesByPostId(int postId);
    boolean isLikedByUser(int userNum, int postId);

}