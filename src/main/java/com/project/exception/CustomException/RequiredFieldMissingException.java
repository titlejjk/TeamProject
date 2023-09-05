package com.project.exception.CustomException;

//게시글 작성시 필수 입력요소가 누락되었을 떄 발생시킬 예외
public class RequiredFieldMissingException extends RuntimeException{

    public RequiredFieldMissingException(String message) {super(message);}
}
