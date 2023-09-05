package com.project.exception;

import com.project.exception.CustomException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    //BAD_REQUEST 상태코드 및 예외처리 메세지 반환
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

    //유효한 토큰인지 조회
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<String> handleTokenInvalidException(TokenInvalidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    //유효한 회원인지 조회
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //회원의 비밀번호 변경시 예외처리 메세지 반환
    @ExceptionHandler(PasswordUpdateException.class)
    public ResponseEntity<String> handlePasswordUpdateException(PasswordUpdateException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //필수 입력요소가 누락되었을 때 발생시킬 예외
    @ExceptionHandler(RequiredFieldMissingException.class)
    public ResponseEntity<String> handlerRequiredFieldMissingException(RequiredFieldMissingException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

    //필수 이미지가 누락되었을 때 발생시킬 예외
    @ExceptionHandler(ImageMissingException.class)
    public ResponseEntity<String> handlerImageMissingException(ImageMissingException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

}
