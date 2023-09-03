package com.project.recipe.like.service;

import com.project.recipe.like.dto.LikeDto;

public interface LikeService {

    String toogleLike(LikeDto dto);

    int countLike(int rcpNum);
}
