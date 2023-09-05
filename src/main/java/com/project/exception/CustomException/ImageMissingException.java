package com.project.exception.CustomException;

//필수 이미지가 업로드되지 않았을 떄 발생시킬 예외
public class ImageMissingException extends RuntimeException{

    public ImageMissingException(String message){super(message);}
}
