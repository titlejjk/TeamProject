package com.project.recipe.board.dto;

import com.project.recipe.image.sub.dto.SubImgDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter @Setter
@NoArgsConstructor  //기본 생성자 생성
@AllArgsConstructor  //인자로 모든 값이 전달되는 생성자 생성
public class BoardDto {
    private int rcpNum;  //게시글 번호 (PK)
    private int userNum;  //작성자 번호
    private int petNum;  //반려동물 타입 번호
    private String title;  //글 제목
    private String content;  //조리순서
    private int servingSize;  //제공 분량
    private int cookingTime;  //조리 시간
    private String cookingLevel;  //난이도
    private String ingredients;  //재료
    private int viewCount;  //조회수
    private String regdate;  //글 작성일
    private String mainPath;  //메인 이미지 파일 경로

    private String userNickname; //글 작성자
    private String mainOrgName;  //메인 이미지 원본 파일명
    private String mainSaveName;  //메인 이미지 저장 파일명
    private MultipartFile imageFile;  //이미지 파일 업로드 처리
    private List<SubImgDto> subImgs; //서브 이미지들
}
