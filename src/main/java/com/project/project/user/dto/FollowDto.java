package com.project.project.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowDto {

    private int followId; //팔로우 관계의 고유 ID
    private String followerEmail; //팔로우하는 회원의 이메일
    private String followingEmail; //팔로우된 회원의 이메일
    private String createdAt; //팔로우가 된 시각
}
