package com.project.project.user.service;

import com.project.project.user.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    //한명의 회원의 정보를 업데이트
    void updateUserInfo(UserDto userDto, MultipartFile mFile);

    //한명의 회원탈퇴 Update Status
    void deactivateUser(int userNum);

}
