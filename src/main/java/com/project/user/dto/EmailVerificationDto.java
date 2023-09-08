package com.project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmailVerificationDto {

    private final String userEmail; //회원의 이메일
    private final String verificationCode; //인증코드
    private final boolean isVerified; //인증여부
    private final LocalDateTime expiryDate; //만료날짜
}
