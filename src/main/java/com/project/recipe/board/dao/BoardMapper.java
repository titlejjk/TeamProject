package com.project.recipe.board.dao;

import com.project.project.recipe.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //DB작업에 필요한 sql 쿼리를 정의/매핑 => DB와 상호작용이 용이해짐
@Repository
public interface BoardMapper {
    //글 추가
    void insertRcp(BoardDto dto);
    //글 수정
    void updateRcp(BoardDto dto);
    //글 삭제
    void deleteRcp(int rcpNum);
    //글 목록
    List<BoardDto> getList();
    //글 정보 얻어오기
    BoardDto getDetail(int rcpNum);  //하나의 rcpNum에 대해
    //글 정보 얻어오기 (키워드 활용  ->  e.g.검색)
//    RecipeDto getData(RecipeDto dto);
    //글 개수
    int getCount(BoardDto dto);
    //조회수 증가
    void addViewCount(int num);

}
