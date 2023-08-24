package com.project.project.user.controller;

import com.project.project.user.dto.SignInResponseDto;
import com.project.project.user.dto.SignUpRequest;
import com.project.project.user.dto.UserDto;
import com.project.project.user.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<?>signUp(@RequestBody SignUpRequest request){
        System.out.println(request);
        authService.signUp(request);

        return ResponseEntity.ok("회원가입 성공");

    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<?>signIn(@RequestBody UserDto request){
        SignInResponseDto response = authService.authenticateUser(
                request.getUserEmail(),
                request.getUserPassword()

        );
        System.out.println(request);
        return ResponseEntity.ok(response);
    }

    //회원가입시 이메일 중복 체크
    @GetMapping("/check-email")
    public ResponseEntity<?>checkEmailDuplication(@RequestParam String userEmail){
        boolean isDuplicated = authService.isEmailDuplicated(userEmail);
        return ResponseEntity.ok().body(isDuplicated ? "Duplicated" : "Not Duplicated");
    }

}
