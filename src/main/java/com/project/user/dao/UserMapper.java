package com.project.user.dao;

import com.project.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper//왜 mapper로 썼나?
public interface UserMapper {
    //Mapper 회원목록조회
    List<UserDto> getAllUsers();
    //이메일 중복 체크
    int checkEmailDuplication (String userEmail);

    //회원의 닉네임 중복 체크
    int findUserByNickname(String userNickname);

    //회원가입 정보 저장
    void insertUser(UserDto userDto);

    int getLastInsertUserNum();

    //이메일을 기반으로 사용자 정보 조회
    UserDto findByEmail(String userEmail);

    //회원정보 수정
    void updateUser(UserDto userDto);

    //회원탈퇴(UserStatus Update)
    void updateUserStatus(@Param("status") String status, @Param("userNum")int userNum);

    //유저소셍(UserStatus Update)
    void userActive(@Param("status") String status, @Param("userNum")int userNum);

    //회원의 비밀번호 변경
    void updatePassword(UserDto userDto);

    //유효한 회원조회 여부
    UserDto findById(String userEmail);

    //회원의 프로필 사진과 한 줄 소개 조회
    UserDto findProfileAndIIntroduction(String userEmail);
}
