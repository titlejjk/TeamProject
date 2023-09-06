package com.project.party_post.post.dto;

import com.project.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private int postId; //게시글 ID
    private int userNUm; //작성자 ID
    private String title; //게시글 제목
    private String content; //게시글 내용
    private String createdAt;  // 게시물 생성 날짜
    private String updatedAt;  // 게시물 수정 날짜
    private int viewCount; //조회수 필드
}
