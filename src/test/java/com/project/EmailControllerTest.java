package com.project;

import com.project.user.controller.EmailController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class EmailControllerTest {

    @Autowired
    private EmailController emailController;

    @Test
    public void testSendEmail() {
        String result = emailController.sendEmail("test@example.com");
        assertEquals("Email sent successfully.", result);
    }

    @Test
    public void testVerifyCode() {
        String result = emailController.verifyCode("test@example.com", "123456"); // 123456은 예시입니다.
        assertEquals("Email verified successfully.", result);
    }
}
