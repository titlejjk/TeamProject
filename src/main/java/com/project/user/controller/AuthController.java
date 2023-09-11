package com.project.user.controller;

import com.project.security.TokenProvider;
import com.project.user.dto.SignInResponseDto;
import com.project.user.dto.SignUpRequest;
import com.project.user.dto.UserDto;
import com.project.user.service.AuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    //AuthServiceImpl 의존성
    private final AuthServiceImpl authService;
    //TokenBlacklist
    private final Set<String> tokenBlacklist = Collections.synchronizedSet(new HashSet<>());
    private final TokenProvider tokenProvider;

    //회원가입
    @ApiOperation(value = "회원 가입")
    @PostMapping
    public ResponseEntity<?>registerUser(@RequestBody SignUpRequest request){
        authService.signUp(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?>authenticateUser(@RequestBody UserDto request, HttpServletResponse response) {
        SignInResponseDto signInResponseDto = authService.authenticateUser(
                request.getUserEmail(),
                request.getUserPassword()
        );
        //Authorization 헤더에 토큰 설정
        response.setHeader("Authorization", "Bearer " + signInResponseDto.getToken());
        System.out.println(response);
        return ResponseEntity.ok(signInResponseDto);
    }
    //회원가입시 이메일 중복 체크
    @GetMapping("/check-email")
    public ResponseEntity<?>checkEmailDuplication(@RequestParam String userEmail){
        boolean isDuplicated = authService.isEmailDuplicated(userEmail);
        return ResponseEntity.ok().body(isDuplicated ? "Duplicated" : "Not Duplicated");
    }
    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization")String token){
        //클라이언트에서 토큰을 삭제하도록
        tokenBlacklist.add(token);
        return ResponseEntity.ok("Logged out successfully");
    }
    //인증 필터나 인터셉터에서 블랙리스트확인
    //블랙리스트에 있는 토큰을 사용한 요청은 거부

    //token 검증
    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);//"Bearer "삭제하고 token값으로 넣기
        try{
            String userEmail = tokenProvider.validate(token);
            String tokenCheck = tokenProvider.validate(token);
            return ResponseEntity.ok("Token is valid. User Email : " + userEmail + "Tokenis valid. User Token : " + tokenCheck);


        }catch (Exception e){
            //토큰검증 실패
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
