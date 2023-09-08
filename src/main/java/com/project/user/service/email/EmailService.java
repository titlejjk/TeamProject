package com.project.user.service.email;

public interface EmailService{

    /*
        이메일을 보내는 메서드
        to 수신자 이메일 주소
        subject 이메일 제목
        text 이메일 본문
     */
    void sendEmail(String to, String subject, String text);

    //6자리 인증 코드를 생성하는 메서드
    String generateVerificationCode();

    /*
        인증 코드를 검증하는 메서드
        inputCode 사용자가 입력한 인증코드
        sentCode 이메일로 보낸 인증 코드
        인증 성공 여부를 return한다.
     */
    boolean verifyCode(String inputCode, String sentCode);
}
