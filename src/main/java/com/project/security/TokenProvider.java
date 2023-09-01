package com.project.security;

import com.project.user.dto.UserDto;
import com.project.user.dto.UserEnumClass.UserRole;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    //JWT 서명 및 검증에 사용되는 키

    private final static String SECURITY_KEY = "jwtseckey!@";


    //사용자 이메일을 받아 JWT를 생성하는 메서드
    public String create(UserDto userDto){

        //토큰에 User의 정보를 넣어주기.
        Claims claims = Jwts.claims().setSubject(userDto.getUserEmail());
        claims.put("userNum", userDto.getUserNum());
        claims.put("userEmail", userDto.getUserEmail());
        claims.put("userNickname", userDto.getUserNickname());
        claims.put("userTypes", userDto.getPetTypes());
        claims.put("roles", userDto.getRole());
        //현재 시간으로부터 1시간으로 설정
        Duration tokenDuration = Duration.ofHours(1);
        //exprTime에 설정한 시간을 java.util.Date 형태로 담아주기
        Date exprTime = Date.from(Instant.now().plus(tokenDuration));
        //JWT생성
        return Jwts.builder()
                //암호화 알고리즘, 키
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                //토큰에 claims담기
                .setClaims(claims)
                //발급현재시간
                .setIssuedAt(new Date())
                //만료시간
                .setExpiration(exprTime)
                //JWT문자열 반환
                .compact();
    }

    //매개변수로 전달된 토큰을 검증하고 , 복호화하여 페이로드에서 제목을 추출하는 역할
    public  String validate(String token){
       try {
           Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
           return claims.getSubject();
       }catch (SecurityException | MalformedJwtException e){
           log.info("잘못된 JWT 서명입니다.");
       }catch (ExpiredJwtException e) {
           log.info("만료된 JWT 토큰입니다.");
       }catch(UnsupportedJwtException e){
           log.info("지원되지 않는 JWT 토큰입니다.");
       }catch(IllegalArgumentException e){
           log.info("JWT 토큰이 잘못되었습니다.");
       }
       return null;
    }
    //주어진 JWT토큰을 파싱하여 사용자의 역할(role)을 반환하는 메서드
    public String getRoleByToken(String token) {
        String role = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody().get("roles").toString();
        //가져온 role을 문자열로 반환
        return UserRole.valueOf(role).getValue();
    }
}
