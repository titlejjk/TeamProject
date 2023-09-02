package com.project.recipe.reply.dao;

import com.project.recipe.reply.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {
    void insertRpl(ReplyDto dto);  //댓글 추가
    void updateRpl(ReplyDto dto);  //댓글 수정
    void deleteRpl(int rplNum);  //댓글 삭제
    List<ReplyDto> getList(ReplyDto dto);  //댓글 목록 반환
    int getCount(int rcpNum);  //해당 게시글에 대한 댓글 개수를 반환


}
