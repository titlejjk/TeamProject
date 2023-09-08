package com.project.user.service.email;

import com.project.user.dao.EmailVerificationMapper;
import com.project.user.dto.EmailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService{

    //EmailVerificationMapper의존성 추가
    private final EmailVerificationMapper emailVerificationMapper;

    //JavaMailSender 의존성 추가
    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String userEmail) {
        //랜덤 인증 코드 생성
        Random random = new Random();
        String verificationCode = String.format("%04d", random.nextInt(10000));

        //만료 시간생성(현재시간 + 10분)
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(10);

        //Database에 저장
        EmailVerificationDto emailVerificationDto = EmailVerificationDto.builder()
                .userEmail(userEmail)
                .verificationCode(verificationCode)
                .expiryDate(expiryDate)
                .build();
        //builder로 저장된 값들을 db에 저장
        emailVerificationMapper.insertEmailCode(emailVerificationDto);

        //저장후 이메일 전송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
    }

    @Override
    public boolean verifyEmail(String userEmail, String verificationCode) {
        return false;
    }
}
