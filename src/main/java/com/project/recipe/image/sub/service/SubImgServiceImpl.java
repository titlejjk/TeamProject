package com.project.recipe.image.sub.service;

import com.project.recipe.board.dto.BoardDto;
import com.project.recipe.board.service.ImageUploadService;
import com.project.recipe.image.sub.dao.SubImgMapper;
import com.project.recipe.image.sub.dto.SubImgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubImgServiceImpl implements SubImgService {
    @Autowired
    private SubImgMapper imgMapper;

    @Autowired
    private ImageUploadService imageUpload;

    //파일 업로드 경로
    @Value("${file.location}")
    private String imgPath;

//    //dto에서 이미지를 업로드하고, 업로드된 이미지 경로를 반환
//    private String uploadAndInsertImage(BoardDto dto){
//        return imageUpload.uploadFile(dto.getImageFile());
//    }

    //서브이미지 저장 처리 메소드
    @Override
    public void saveImg(int rcpNum, List<MultipartFile> subImgs) {
        List<String> imagePaths = new ArrayList<>();
        int i = 1;
        for (MultipartFile subImg : subImgs){
            //서브 이미지 파일을 업로드하고 경로를 가져옴
            String imagePath = imageUpload.uploadFile(subImg);
            imagePaths.add(imagePath);

            SubImgDto dto = new SubImgDto();
            dto.setRcpNum(rcpNum);
            dto.setSubPath(imagePath);
            dto.setSubOrder(i);
           // dto.setSubOrgName(subImg.getOriginalFilename());
            //dto.setSubSaveName(subImg.getOriginalFilename());
            //dto에 저장된 이미지 파일 추출
            imgMapper.insertImg(dto);
            i++;
        }

    }

    //서브이미지 삭제 처리 메소드
    @Override
    public void deleteImg(int subNum) {
        imgMapper.deleteImg(subNum);
    }

    //서브이미지 목록 처리 메소드
    @Override
    public List<SubImgDto> getImgs(int subNum) {

        return imgMapper.getImgs(subNum);
    }


}
