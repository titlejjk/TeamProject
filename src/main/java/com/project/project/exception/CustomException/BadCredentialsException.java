package com.project.project.exception.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//잘못된 자격 증명 예외처리
//회원이 로그인시 사용자 정보가 없거나 비밀번호가 누락된 경우 발생하는 예외
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadCredentialsException extends RuntimeException{

    //생성자에서 예외 메세지를 받아 부모 클래스로 전달
    public BadCredentialsException(String message){
        super(message);
    }
}
