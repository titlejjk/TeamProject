package com.project.recipe.image.sub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubImgDto {
    private Integer subNum;  //서브 이미지 번호
    private int rcpNum;  //게시글 번호
    private String subOrgName;  //서브 이미지 원본 파일명
    private String subSaveName;  //서브 이미지 저장 파일명
    private String subPath;  //서브 이미지 파일 경로
    private int subOrder;  //서브 이미지 순서
    private MultipartFile imageFile;  //이미지 파일 처리
}
