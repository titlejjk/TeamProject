package com.project.recipe.image.sub.controller;

import com.project.recipe.image.sub.dto.SubImgDto;
import com.project.recipe.image.sub.service.SubImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recipe/subImg")
public class SubImgController {

    @Autowired
    private SubImgService subImgService;

    //서브이미지 목록  (필요 여부 확인!!)
    @GetMapping("/list")
    public List<SubImgDto> list(@RequestParam int rcpNum){
        List<SubImgDto> list = new ArrayList<>();
        list = subImgService.getImgs(rcpNum);
        return list;
    }

    //서브이미지 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImg(@RequestParam int subNum){
        subImgService.deleteImg(subNum);
        return new ResponseEntity<>("Delete Complete!", HttpStatus.OK);
    }
}
