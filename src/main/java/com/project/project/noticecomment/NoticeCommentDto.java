package com.project.project.noticecomment;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NoticeCommentDto {


    private Long id;                       // 댓글 번호 (PK)
    private Long postId;                   // 게시글 번호 (FK)
    private String content;                // 내용
    private String writer;                 // 작성자
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
