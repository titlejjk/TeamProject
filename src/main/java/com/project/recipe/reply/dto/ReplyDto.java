package com.project.recipe.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private int rplNum;  //댓글 번호
    private int rcpNum; //게시글 번호
    private String rplWriter;  //댓글 작성자
    private String rplContent;  //댓글 내용
    private String rplTarget;  //댓글 대상자
    private int rplGroup;  //원댓글의 (그룹)번호
    private String deleted;  //댓글 삭제 여부 ('yes' or 'no')
    private String regdate;  //댓글 작성일
    private String profile;  //댓글 작성자 프로필

    private int startRowNum;  //첫번째 행
    private int endRowNum;  //마지막 행
}
