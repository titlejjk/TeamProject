package com.project.recipe.like.dao;

import com.project.recipe.like.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    //좋아요 여부 확인을 위해 개수 파악
    int countLike(int rcpNum);
    //좋아요 추가
    void insertLike(LikeDto dto);
    //좋아요 삭제
    void deleteLike(LikeDto dto);

}
