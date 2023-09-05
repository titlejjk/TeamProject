package com.project.recipe.like.service;

import com.project.recipe.like.dto.LikeDto;

import java.util.List;

public interface LikeService {

    String toogleLike(LikeDto dto);

    int countLike(LikeDto dto);

    int countedLike(int rcpNum);

    boolean isLikedByUser(LikeDto dto);

    List<LikeDto> orderByLike();
}
