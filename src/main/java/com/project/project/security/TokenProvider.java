package com.project.project.security;

import com.project.project.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {

    //JWT 서명 및 검증에 사용되는 키

    private final static String SECURITY_KEY = "jwtseckey!@";


    //사용자 이메일을 받아 JWT를 생성하는 메서드
    public String create(UserDto userDto){
        //토큰에 담을 클레임 정의
        Claims claims = Jwts.claims().setSubject(userDto.getUserEmail());
        claims.put("userNum", userDto.getUserNum());
        claims.put("userEmail", userDto.getUserEmail());
        claims.put("userNickname", userDto.getUserNickname());
        claims.put("petTypes", userDto.getPetTypes());
        claims.put("petTypeIds", userDto.getPetTypeIds());
        //토큰 만료시간 = 현재시간 + 1시간
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        //JWT생성
        return Jwts.builder()
                //암호화 알고리즘, 키
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                //토큰에 claims담기
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(exprTime)
                //JWT문자열 반환
                .compact();
    }

    //매개변수로 전달된 토큰을 검증하고 , 복호화하여 페이로드에서 제목을 추출하는 역할
    public  String validate(String token){
        //토큰을 검증하기 위해 키를 설정
        //토큰을 복호화하고, 페이로드의 내용을 추출
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        //추출한 페이로드에서 제목을 가져와 변환
        //제목은 토큰에 설정된 사용자 이메일
        return claims.getSubject();
    }
}
