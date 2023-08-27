package com.project.recipe.board.service;

import com.project.project.recipe.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    void saveContent(BoardDto dto);

    void updateContent(BoardDto dto);

    void deleteContent(int rcpNum);

    List<BoardDto> getList();

    BoardDto getDetail(int rcpNum);
}
