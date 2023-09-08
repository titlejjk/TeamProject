package com.project.user.controller;

import com.project.user.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    //이메일 전송 API
    @PostMapping("/send")
    public String sendEmail(@RequestParam String userEmail){
        String verificationCode = emailService.generateVerificationCode();
        emailService.sendEmail(userEmail,"인증코드 확인 이메일 ", "회원님의 인증 코드는 다음과 같습니다 : " + verificationCode);

        return "Email sent successfully.";
    }

    //인증 코드 검증 API
    @PostMapping("/verify")
    public String verifyCode(@RequestParam String userEmail, @RequestParam String verificationCode){
        //데이터베이스에서 해당 이메일의 코드를 가져오는 로직
        boolean isVerified = emailService.verifyCode(verificationCode, "인증코드입니다.");
        if(isVerified){
            return "이메일 인증이 성공했습니다.";
        }else{
            return "이메일 인증의 실패했습니다.";
        }
    }
}
