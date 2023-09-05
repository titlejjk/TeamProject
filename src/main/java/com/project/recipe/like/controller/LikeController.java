package com.project.recipe.like.controller;

import com.project.recipe.like.dto.LikeDto;
import com.project.recipe.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe/like")
public class LikeController {
    @Autowired
    private LikeService likesService;

    //좋아요 토글
    @Transactional
    @PostMapping("/toggle")
    public ResponseEntity<String> toogleLike(@RequestBody LikeDto dto){
        String result = likesService.toogleLike(dto);
        HttpStatus status = "Like Inserted".equals(result) ? HttpStatus.OK : HttpStatus.BAD_REQUEST ;
        return new ResponseEntity<>(result, status);
    }

    //좋아요 개수
    @GetMapping("/counted")
    public ResponseEntity<Integer> countedLike(@RequestParam int rcpNum){
        int result = likesService.countedLike(rcpNum);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //좋아요 초기화 방지
    @GetMapping("/isLiked")
    public ResponseEntity<Boolean> isLikedByUser(@RequestBody LikeDto dto){
        boolean result = likesService.isLikedByUser(dto);
        HttpStatus status = result == true ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(result, status);
    }

    //좋아요 순위별 조회
    @GetMapping("/order")
    public ResponseEntity<?> orderByLike(){
        List<LikeDto> result = likesService.orderByLike();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
