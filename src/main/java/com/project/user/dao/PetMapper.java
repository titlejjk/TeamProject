package com.project.user.dao;

import com.project.user.dto.UserPetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PetMapper {

    //사용자의 반려동물 정보 저장
    void insertUserPet(UserPetDto userPetDto);

    int getLastInsertPetId();

    //반려동물 유형 정보 조회
    List<Map<String, Object>>findPetTypesByUserNum(int userNum);

    //반려동물 정보 삭제
    void deletePetsByUserNum(int userNum);

    //반려동물 정보 입력
    void insertPets(UserPetDto userPetDto);
}
