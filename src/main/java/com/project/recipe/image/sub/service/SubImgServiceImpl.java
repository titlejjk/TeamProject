package com.project.recipe.image.sub.service;

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
import java.util.List;
import java.util.UUID;

@Service
public class SubImgServiceImpl implements SubImgService {
    @Autowired
    private SubImgMapper imgMapper;

    //파일 업로드 경로
    @Value("${file.location}")
    private String imgPath;

    //서브이미지 저장 처리 메소드
    @Override
    public void saveImg(int rcpNum, List<MultipartFile> subImgs) {
        int i = 1;
        for(MultipartFile subImg : subImgs){
            SubImgDto dto = new SubImgDto();
            dto.setRcpNum(rcpNum);
            dto.setImageFile(subImg);
            dto.setSubOrder(i);
            dto.setSubOrgName(subImg.getOriginalFilename());
            //Dto에 담긴 파일을 추출 (업로드된 파일 가져오기)
                //첨부 파일이 없을 경우
            if(subImg.isEmpty()){
                System.out.println("이미지 업로드 필요");
                //첨부 파일이 있을 경우
            }else{
                try{
                    //원본 파일명을 불러옴
                    String subOrgName = subImg.getOriginalFilename();
                    //서버 저장용 파일명을 생성
                    String subSaveName = UUID.randomUUID().toString() + subOrgName;
                    //저장경로 설정
                    String subPath = imgPath + File.separator + subSaveName;
                    //폴더가 없을 경우 폴더 생성
                    File uploadFile = new File(imgPath);
                    if(!uploadFile.exists()){
                        uploadFile.mkdir();
                    }
                    //해당 경로에 파일 저장 (서버에 저장)
                    Path path = Paths.get(subPath).toAbsolutePath();
                    //Path 객체를 File 객체로 변환하여 업로드 (Path 객체 사용시 경로를 더 안전하게 처리할 수 있음)
                    subImg.transferTo(path.toFile());
                    dto.setSubSaveName(subSaveName);
                    dto.setSubPath(subPath);
                    //DB에 서브 이미지 정보 저장
                    imgMapper.insertImg(dto);
                    i++;
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
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
