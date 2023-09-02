package com.project.party_post.dto;

import com.project.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class PostDto {

    private final int postId;
    private final UserDto user;
    private final String title;
    private final String content;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
}
