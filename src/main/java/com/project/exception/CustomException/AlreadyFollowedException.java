package com.project.exception.CustomException;

//이미 팔로우한 사용자를 다시 팔로우 할 때 발생하는 사용자 정의 예외처리
public class AlreadyFollowedException extends RuntimeException{

    //생성자에서 예외 메세지를 받아 부모 클래스로 전달
    public AlreadyFollowedException(String message){
        super(message);
    }
}
