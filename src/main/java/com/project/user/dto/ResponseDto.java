package com.project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {

    private boolean result; // 응답 결과를 나타내는 필드
    private String message; // 응답 메시지를 나타내는 필드
    private D data;

    public static <D> ResponseDto<D> setSuccess(String message, D data){
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message){
        return ResponseDto.set(false, message, null);
    }
}
