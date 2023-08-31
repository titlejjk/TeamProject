package com.project.security;

import com.project.exception.CustomException.BadCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    //사용자 인증을 담당하는 클래스
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String userPassword = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        if(userDetails != null && passwordEncoder.matches(userPassword, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userEmail, userPassword, userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("사용자의 계정 정보가 불일치 합니다.");
        }
    }

    /*
        AuthenticationProvider가 UsernamePasswordAuthentiacationToken타입의 인증을
        지원하는지 여부를 반환
    */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
