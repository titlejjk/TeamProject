package com.project.user.service;

import com.project.user.dto.SignInResponseDto;
import com.project.user.dto.SignUpRequest;

public interface AuthService {

   //회원가입메서드
    void signUp(SignUpRequest signUpRequest);
    //회원로그인메서드
    SignInResponseDto authenticateUser(String userEmail, String userPassword);

    //회원의 이메일 중복체크

    boolean isEmailDuplicated(String userEmail);
}
