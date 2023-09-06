package com.project.file_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${file.location}")//파일 저장 경로(application.yml에서 설정)
    private String fileLocation;

    public String uploadFile(MultipartFile file, String subDirectory){
        //원본 파일 이름
        String originalFilename = file.getOriginalFilename();
        //저장할 파일 이름 (UUID 를 추가하여 중복 방지)
        String saveFileName = UUID.randomUUID().toString() + "_" + originalFilename;
        //실제 파일 저장 경로
        String realPath = fileLocation + File.separator + subDirectory;

        //업로드 폴더 객체 생성
        File upload = new File(realPath);
        if(!upload.exists()){
            upload.mkdirs();
        }
        try{
            //저장할 전체 경로 구성
            String savePath = realPath + File.separator + saveFileName;
            //임시 폴더에 업로드된 파일을 원하는 경로에 저장
            file.transferTo(new File(savePath));
        }catch (Exception e){
            e.printStackTrace();//예외처리
        }
        //저장된 이미지 경로 반환
        return "/" + subDirectory + "/" + saveFileName;
    }
}
