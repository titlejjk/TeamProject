package com.project.user.service;

import com.project.user.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //서빗,인터페스 회원목록조회
    List<UserDto> getAllUsers();

    //한명의 회원의 정보를 업데이트
    String updateUser(UserDto userDt) throws IllegalAccessException;

    //한명의 회원탈퇴 Update Status
    void deactivateUser(int userNum);

    //회원의 비밀번호 변경
    String  updatePassword(UserDto userDto);

    //한명의 유저소셍 Update Status
    void userActive(int userNum);

    //회원의 프로필사진과 한 줄 소개 조회
    UserDto getUserProfileAndIntroduction(String userEmail);

}
