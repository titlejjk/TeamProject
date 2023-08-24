package com.project.project.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LikesDto {

    private int cNum;
    private int userNum;
    private String likeUserProfile;
    private String likeUserNickname;

}
