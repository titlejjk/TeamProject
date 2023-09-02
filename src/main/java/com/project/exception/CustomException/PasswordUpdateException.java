package com.project.exception.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//회원의 비밀번호 수정시 예외처리 발생
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PasswordUpdateException extends RuntimeException{
    public PasswordUpdateException(String message){
        super(message);
    }
}
