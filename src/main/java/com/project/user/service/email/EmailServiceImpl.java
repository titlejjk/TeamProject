package com.project.user.service.email;

import com.project.user.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    //JavaMailSener 의존성주입
    private final JavaMailSender mailSender;
    //인증코드 생성 랜덤 객체
    private Random random = new Random();
    //이메일 발송 메서드
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    //6자리 인증 코드 생성 메서드
    @Override
    public String generateVerificationCode() {
        return String.format("%06d", random.nextInt(1000000));
    }

    //인증 코드가 유효한지 검증
    @Override
    public boolean verifyCode(String inputCode, String sentCode) {
        return inputCode.equals(sentCode);
    }
}
