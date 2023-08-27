package com.project.recipe.reply.dao;

import com.project.project.recipe.reply.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    void insertRpl(ReplyDto dto);  //댓글 추가
//    void updateRpl(RcpRplDto dto);  //댓글 수정
//    void deleteRpl(int rplNum);  //댓글 삭제
//    List<RcpRplDto> getList(RcpRplDto dto);  //댓글 목록 반환
//    RcpRplDto getData(int rplNum);  //댓글 하나의 정보를 반환
//    int getCount(int rplGroup);  //댓글의 개수를 반환

}
