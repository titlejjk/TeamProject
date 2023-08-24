package com.project.project.exception;

import com.project.project.exception.CustomException.BadCredentialsException;
import com.project.project.exception.CustomException.EmailAlreadyExistsException;
import com.project.project.exception.CustomException.NicknameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

//전역 예외 처리 클래스
//App내의 발생하는 예외처리를 위해 만듬
@RestControllerAdvice
public class GlobalExceptionHandler {

    //이메일 중복 예외 처리 메서드
    //BAD_REQUEST 상태코드 및 예외처리 메세지반환
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //닉네임 중복 예외 처리 메서드
    //BAD_REQUEST 상태코드 및 예외처리 메세지반환
    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public ResponseEntity<String> handleNicknameAlreadyExistsException(NicknameAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    //잘못된 자격 증명 예외 처리 메서드
    //UNAUTHORIZED 상태코드 및 예외처리 메세지 반환
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    //로그 기록, 사용자에게 메세지 전달 등의 처리
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?>handleAccessDeniedException(AccessDeniedException ex){
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }
}
