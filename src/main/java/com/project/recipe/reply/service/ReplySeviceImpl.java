package com.project.recipe.reply.service;

import com.project.recipe.board.dto.BoardDto;
import com.project.recipe.reply.dao.ReplyMapper;
import com.project.recipe.reply.dto.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReplySeviceImpl implements ReplyService {

    @Autowired
    private ReplyMapper rplMapper;

    //댓글 저장
    @Override
    public void saveReply(ReplyDto dto) {
        rplMapper.insertRpl(dto);
    }

    //댓글 수정
    @Override
    public void updateReply(ReplyDto dto) {
        rplMapper.updateRpl(dto);
    }

    //댓글 삭제
    @Override
    public void deleteReply(int rplNum) {
        rplMapper.deleteRpl(rplNum);
    }

    //게시글에 대한 댓글 목록 반환
    @Override
    public List<ReplyDto> getRplList(int rcpNum) {
//        //한 페이지에 보여줄 게시글 개수
//        final int PAGE_ROW_COUNT = 5;
//        //하단에 보여줄 페이지 번호 개수
//        final int PAGE_DISPLAY_COUNT = 5;
//        //현재 페이지에 표시할 게시글의 인덱스를 계산
//        int startRowNum = 1 + (pageNum - 1)*PAGE_ROW_COUNT;
//        int endRowNum = pageNum * PAGE_ROW_COUNT;
        //Dto객체에 시작과 끝 인덱스를 설정
        ReplyDto dto = new ReplyDto();
        dto.setRcpNum(rcpNum);
//        dto.setStartRowNum(startRowNum);
//        dto.setEndRowNum(endRowNum);
        //해당 게시글 번호에 대한 댓글 목록 조회
        List<ReplyDto> rplList = rplMapper.getRplList(dto);
//        //해당 게시글 번호에 대한 댓글 개수 조회
//        int totalRow = rplMapper.getCount(rcpNum);
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
//        result.put("totalPageCount", totalPageCount);
//        result.put("rplList", rplList);
//        result.put("totalRow", totalRow);
        //페이징 처리된 댓글 목록 반환
        return rplList;
    }

    //내가 작성한 댓글 목록 반환
    @Override
    public List<ReplyDto> getMyRplList(int userNum, int rcpNum) {
        ReplyDto dto = new ReplyDto();
        dto.setUserNum(userNum);
        dto.setRcpNum(rcpNum);
        List<ReplyDto> myRplList = rplMapper.getMyRplList(dto);
        return myRplList;
    }
}
