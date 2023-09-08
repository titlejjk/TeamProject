package com.project.user.service.email;

public interface EmailVerificationService {

    //사용자에게 이메일을 전송
    void sendVerificationEmail(String userEmail);

    //사용자가 입력한 인증 코드를 검증
    boolean verifyEmail(String userEmail, String verificationCode);
}
