package com.project.recipe.board.controller;

import com.project.exception.CustomException.ImageMissingException;
import com.project.recipe.board.dto.BoardDto;
import com.project.recipe.board.service.BoardService;
import com.project.recipe.image.sub.service.SubImgService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/recipe")
public class BoardController {

    @Autowired
    private BoardService rcpService;
    @Autowired
    private SubImgService subImgService;

    //게시글 작성
    @PostMapping("/insert")
    @Transactional //Transaction 처리 (하나라도 실패하면 전체 roll back)
    public ResponseEntity<?> insert(@ModelAttribute BoardDto dto,
                                    @RequestPart(value="subImg", required = false) List<MultipartFile> subImg){
        try {
            //게시글 내용 저장
            rcpService.saveContent(dto);
            //서브 이미지 저장
            subImgService.saveImg(dto.getRcpNum(), subImg);

            //저장 완료시 성공메시지 출력
            return new ResponseEntity<>("Insert Complete!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Insert", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //게시글 수정
    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute BoardDto dto){
        rcpService.updateContent(dto);
        return new ResponseEntity<>("Update Complete!", HttpStatus.OK);
    }

    //게시글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int rcpNum){
        //게시글 번호로 해당 게시글 삭제
        rcpService.deleteContent(rcpNum);
        return new ResponseEntity<>("Delete Complete!", HttpStatus.OK);
    }

    //전체 게시글 목록
    @GetMapping("/list")
    public List<BoardDto> getList(@RequestParam(name="keyword", required = false)String keyword,
                                  @RequestParam(name="condition", required = false)String condition){
        return rcpService.getList(keyword, condition);
    }

    //게시글 상세
    @GetMapping("/detail")
    public BoardDto getDetail(@RequestParam int rcpNum){
        return rcpService.getDetail(rcpNum);
    }

    //나의 게시글 목록
    @GetMapping("/myList")
    public List<BoardDto> getMyList(@RequestParam int userNum){
        return rcpService.getMyList(userNum);
    }

    //카테고리 별 게시글 목록
    @GetMapping("/petList")
    public List<BoardDto> getByCategory(@RequestParam int petNum){
        return rcpService.getByCategory(petNum);
    }
}
