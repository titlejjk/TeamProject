package com.project.recipe.board.service;

import com.project.recipe.board.dao.BoardMapper;
import com.project.recipe.board.dto.BoardDto;
import com.project.recipe.image.sub.dao.SubImgMapper;
import com.project.recipe.image.sub.dto.SubImgDto;
import com.project.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            //5. 폴더가              없을 경우 폴더 생성
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
    public List<BoardDto> getList(String keyword, String condition, Map<String, Object> result) {
//        //한 페이지에 보여줄 게시글 개수
//        final int PAGE_ROW_COUNT = 6;
//        //하단에 보여줄 페이지 번호 개수
//        final int PAGE_DISPLAY_COUNT = 5;
//        //현재 페이지에 표시할 게시글의 인덱스를 계산
//        int startRowNum = 1 + (pageNum - 1)*PAGE_ROW_COUNT;
//        int endRowNum = pageNum * PAGE_ROW_COUNT;
//        //keyword를 인코딩하여 변수에 저장 (null일 경우 빈 문자열로 대체)
//        String encodedK = URLEncoder.encode(keyword == null ? "" : keyword);
//        //Dto객체에 시작과 끝 인덱스를 설정
        BoardDto dto = new BoardDto();
//        dto.setStartRowNum(startRowNum);
//        dto.setEndRowNum(endRowNum);
        //keyword가 있을 경우 검사
        if(keyword != null && !"".equals(keyword)){
            //검색조건이 "작성자"인 경우
            if("userNickname".equals(condition)){
                //"작성자" 검색조건이 선택되었을 때 사용자가 입력한 검색키워드를 writer 필드에 저장
                dto.setUserNickname(keyword);
            }
        }
        //검색조건에 맞는 게시글 목록을 조회
        List<BoardDto> rcpList = rcpMapper.getList(dto);
//        int totalRow = rcpMapper.getCount(dto);
//        //페이지 번호에 따른 시작/끝/총 페이지 수 를 계산
//        int startPageNum = 1 + ((pageNum - 1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
//        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT -1;
//        int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
//        //현재 표시될 끝 페이지 번호가 총 페이지 개수를 초과하지 않도록 조정
//        if(endPageNum > totalPageCount){
//            endPageNum = totalPageCount;
//        }
//        //result 매개변수로 전달받은 Map에 조회결과 및 페이지 정보를 저장
//        result.put("pageNum", pageNum);
//        result.put("startPageNum", startPageNum);
//        result.put("endPageNum", endPageNum);
//        result.put("condition", condition);
//        result.put("keyword", keyword);
//        result.put("encodedK", encodedK);
//        result.put("totalPageCount", totalPageCount);
//        result.put("rcpList", rcpList);
//        result.put("totalRow", totalRow);
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
        //조회수 증가시키기
        rcpMapper.addViewCount(rcpNum);
        //서브 이미지를 추가한 게시글 상세정보 반환
        return rcpDetail;
    }

    @Override
    public List<BoardDto> getRcpList(int userNum, Map<String, Object> result) {
//        //한 페이지에 보여줄 게시글 개수
//        final int PAGE_ROW_COUNT = 9;
//        //하단에 보여줄 페이지 번호 개수
//        final int PAGE_DISPLAY_COUNT = 5;
//        //현재 페이지에 표시할 게시글의 인덱스를 계산
//        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
//        int endRowNum = pageNum * PAGE_ROW_COUNT;
        //Dto객체에 시작과 끝 인덱스를 설정
        BoardDto dto = new BoardDto();
        dto.setUserNum(userNum);
//        dto.setStartRowNum(startRowNum);
//        dto.setEndRowNum(endRowNum);
        //해당 작성자가 작성한 게시글 목록을 조회
        List<BoardDto> myList = rcpMapper.getRcpList(dto);
//        int totalRow = rcpMapper.getRcpCount(userNum);
//        //페이지 번호에 따른 시작/끝/총 페이지 수 를 계산
//        int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
//        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
//        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
//        //현재 표시될 끝 페이지 번호가 총 페이지 개수를 초과하지 않도록 조정
//        if (endPageNum > totalPageCount) {
//            endPageNum = totalPageCount;
//        }
//        //result 매개변수로 전달받은 Map에 조회결과 및 페이지 정보를 저장
//        result.put("pageNum", pageNum);
//        result.put("startPageNum", startPageNum);
//        result.put("endPageNum", endPageNum);
//        result.put("totalPageCount", totalPageCount);
//        result.put("myList", myList);
//        result.put("totalRow", totalRow);
        //이미지 경로 설정 후 dto에 추가
        for (BoardDto recipe : myList) {
            //메인 이미지 파일 경로 생성 (이미지 경로 + 파일명)
            String mainPath = imgPath + File.separator + recipe.getMainSaveName();
            //이미지 파일 존재 여부 확인
            File imgFile = new File(mainPath);
            if (imgFile.exists()) {
                //이미지 파일이 존재하는 경우에만 레시피 객체에 이미지 경로 추가
                recipe.setMainPath(mainPath);
            } else {
                //이미지 파일이 없을 경우 기본 이미지 경로로 설정 (추후 상대경로로 변경)
                String defaultPath = "/Users/sujeong/Documents/workspace/images/default.png";
                recipe.setMainPath(defaultPath);
            }
        }
        //수정된 게시글 목록 반환
        return myList;
    }
}