package com.project.party_post.post_like.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//좋아요 관련 데이터 전송 객체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikesDto {

    private int likeId; //좋아요 ID
    private int postId; //게시글 ID
    private int userNum; //사용자 ID
    private String createdAt; //좋아요 생성 시인

}
