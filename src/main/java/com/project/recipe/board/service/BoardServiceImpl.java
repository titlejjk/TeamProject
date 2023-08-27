package com.project.recipe.board.service;

import com.project.project.recipe.board.dao.BoardMapper;
import com.project.project.recipe.board.dto.BoardDto;
import com.project.project.recipe.image.sub.dao.SubImgMapper;
import com.project.project.recipe.image.sub.dto.SubImgDto;
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
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper rcpMapper;

    @Autowired
    private SubImgMapper subImgMapper;

    //파일 업로드 경로
    @Value("${file.location}")
    private String imgPath;

    //게시글 + 메인이미지 저장 처리 메소드
    @Override
    public void saveContent(BoardDto dto) {
            //첨부 파일이 없을 경우
        if(dto.getImageFile().isEmpty()){
            System.out.println("이미지 업로드 필요");
            //첨부 파일이 있을 경우
        }else{
            //1. Dto에 담긴 파일을 추출
            MultipartFile mainImg = dto.getImageFile();
            //2. 원본 파일명을 불러옴
            String mainOrgName = mainImg.getOriginalFilename();
            //3. 서버 저장용 파일명을 생성
            String mainSaveName = UUID.randomUUID().toString() + mainOrgName;
            //4. 저장경로 설정
            String mainPath = imgPath + File.separator + mainSaveName;
            //5. 폴더가 없을 경우 폴더 생성
            File uploadFile = new File(imgPath);
            if(!uploadFile.exists()){
                uploadFile.mkdir();
            }
            //6. 해당 경로에 파일 저장 (서버에 저장)
            try{
                //mainPath를 기반으로한 Path 객체를 생성한 후 절대경로로 변환
                Path path = Paths.get(mainPath).toAbsolutePath();
                //Path 객체를 File 객체로 변환하여 업로드 (Path 객체 사용시 경로를 더 안전하게 처리할 수 있음)
                mainImg.transferTo(path.toFile());
            }catch(IOException e){
                e.printStackTrace();
            }
            //7. 게시글 + 이미지 등록 (저장)
            rcpMapper.insertRcp(dto);
        }
    }

    //게시글 + 메인이미지 수정 처리 메소드
    @Override
    public void updateContent(BoardDto dto) {
        //새로운 메인 이미지 가져오기
        MultipartFile newMainImg = dto.getImageFile();
        //새로운 이미지가 업로드 되었을 경우
        if(!newMainImg.isEmpty()){
            String mainOrgName = newMainImg.getOriginalFilename();
            //서버 저장용 파일명 생성
            String mainSaveName = UUID.randomUUID().toString() + mainOrgName;
            //저장경로 설정
            String mainPath = imgPath + File.separator + mainSaveName;
            //폴더가 없을 경우 폴더 생성
            File uploadFile = new File(imgPath);
            if(!uploadFile.exists()){
                uploadFile.mkdir();
            }
            //해당 경로에 파일 저장 (서버에 저장)
            try {
                //mainPath를 기반으로 한 Path 객체를 생성한 후 절대경로로 변환
                Path path = Paths.get(mainPath).toAbsolutePath();
                //Path 객체를 File 객체로 변환하여 업로드 (Path 객체 사용시 경로를 더 안전하게 처리할 수 있음)
                newMainImg.transferTo(path.toFile());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //게시글 수정
        rcpMapper.updateRcp(dto);
    }

    //게시글 삭제 처리 메소드
    @Override
    public void deleteContent(int rcpNum) {
        //게시글 번호로 해당 게시글 삭제
        rcpMapper.deleteRcp(rcpNum);
    }

    //게시글 목록 처리 메소드
    @Override
    public List<BoardDto> getList() {
        //게시글 목록 조회
        List<BoardDto> rcpList = rcpMapper.getList();
        //이미지 경로 설정 후 dto에 추가
        for(BoardDto recipe : rcpList){
            //메인 이미지 파일 경로 생성 (이미지 경로 + 파일명)
            String mainPath = imgPath + File.separator + recipe.getMainSaveName();
            //이미지 파일 존재 여부 확인
            File imgFile = new File(mainPath);
            if(imgFile.exists()){
                //이미지 파일이 존재하는 경우에만 레시피 객체에 이미지 경로 추가
                recipe.setMainPath(mainPath);
            }else{
                //이미지 파일이 없을 경우 기본 이미지 경로로 설정 (추후 상대경로로 변경)
                String defaultPath = "/Users/sujeong/Documents/workspace/images/default.png";
                recipe.setMainPath(defaultPath);
            }
        }
        //수정된 게시글 목록 반환
        return rcpList;
    }

    //게시글 상세 처리 메소드
    @Override
    public BoardDto getDetail(int rcpNum) {
        //게시글 상세정보 조회
        BoardDto rcpDetail = rcpMapper.getDetail(rcpNum);
        //게시글 번호로 서브 이미지들 조회
        List<SubImgDto> subImgList = subImgMapper.getImgs(rcpDetail.getRcpNum());
        //게시글 상세정보에 서브 이미지들 추가
        rcpDetail.setSubImgs(subImgList);
        //서브 이미지를 추가한 게시글 상세정보 반환
        return rcpDetail;

//        //이미지 경로 설정 후 dto에 추가
//        for(SubImgDto recipe : subImgList){
//            //서브 이미지 파일 경로 생성 (이미지 경로 + 파일명)
//            String subPath = recipe.getSubSaveName();
//            //이미지 파일 존재 여부 확인
//            File imgFile = new File(subPath);
//            if(imgFile.exists()){
//                //이미지 파일이 존재하는 경우에만 레시피 객체에 이미지 경로 추가
//                recipe.setSubPath(subPath);
//            }else{
//                //이미지 파일이 없을 경우 기본 이미지 경로로 설정 (추후 상대경로로 변경)
//                String defaultPath = "/Users/sujeong/Documents/workspace/images/default.png";
//                recipe.setSubPath(defaultPath);
//            }
//        }

    }
}