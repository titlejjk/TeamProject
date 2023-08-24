package com.project.project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPetDto {

    private int userNum; //회원번호
    private int petTypeId; //반려동물 유형 ID
    private List<Integer> petTypeIds; // petTypeIds 추가
}
