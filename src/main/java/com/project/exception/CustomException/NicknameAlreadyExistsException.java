package com.project.exception.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//회원의 닉네임 중복 예외 처리 클래스
//회원가입시 동일한 이메일이 존재하는 경우의 예외
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NicknameAlreadyExistsException extends RuntimeException{

    //생성자에서 예외 메세지를 받아서 부모 클래스에 전달
    public NicknameAlreadyExistsException(String message){
        super(message);
    }
}
