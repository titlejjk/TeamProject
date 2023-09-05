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
        //Dto객체에 시작과 끝 인덱스를 설정
        ReplyDto dto = new ReplyDto();
        dto.setRcpNum(rcpNum);
        //해당 게시글 번호에 대한 댓글 목록 조회
        List<ReplyDto> rplList = rplMapper.getRplList(dto);
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
