package com.project.recipe.board.service;

import com.project.recipe.board.dto.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    void saveContent(BoardDto dto);

    void updateContent(BoardDto dto);

    void deleteContent(int rcpNum);

    List<BoardDto> getList(String keyword, String condition, Map<String, Object> result);

    BoardDto getDetail(int rcpNum);

    List<BoardDto> getMyList(int userNum, Map<String, Object> result);
}
