package com.project.recipe.reply.dao;

import com.project.recipe.reply.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {
    //댓글 추가
    void insertRpl(ReplyDto dto);
    //댓글 수정
    void updateRpl(ReplyDto dto);
    //댓글 삭제
    void deleteRpl(int rplNum);
    //댓글 목록 반환
    List<ReplyDto> getRplList(ReplyDto dto);
    //내가 작성한 댓글 목록
    List<ReplyDto> getMyRplList(ReplyDto dto);

}
