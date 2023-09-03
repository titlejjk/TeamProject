package com.project.recipe.like.controller;

import com.project.recipe.like.dto.LikeDto;
import com.project.recipe.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe/like")
public class LikeController {
    @Autowired
    private LikeService likesService;

    //좋아요 토글
    @PostMapping("/toggle")
    public ResponseEntity<?> toogleLike(@RequestBody LikeDto dto){
        likesService.toogleLike(dto);
        return new ResponseEntity<>("Likes", HttpStatus.OK);
    }

    //좋아요 개수
    @GetMapping("/count")
    public int countLike(@RequestParam int rcpNum){
        return likesService.countLike(rcpNum);
    }
}
