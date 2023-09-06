package com.project.config;


import com.project.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
// Spring Security 를 활성화
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // 필터를 주입 JWT 인증을 처리하는 데 사용
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

   @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity)throws Exception{
       logger.debug("Configuring Web Security");
       httpSecurity
               .cors().configurationSource(request -> {
                   CorsConfiguration config = new CorsConfiguration();
                   config.setAllowedOrigins(Collections.singletonList("*"));
                   config.setAllowedMethods(Collections.singletonList("*"));
                   config.setAllowedHeaders(Collections.singletonList("*"));
                   return config;
               })
               .and()
               // CSRF (Cross-Site Request Forgery) 보안 대책을 비활성화
               .csrf().disable()
               // HTTP 기본 인증을 비활성화
               .httpBasic().disable()
               // 세션을 사용하지 않고 상태를 유지하지 않는 인증 방식을 설정
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
               // 요청에 대한 인증 규칙을 설정
               .authorizeRequests()
               //회원등급에 대한 api권한부여
               .antMatchers("/","/**").hasAnyAuthority("USER")
               .antMatchers("/admin/**").hasAuthority("ADMIN")
               .antMatchers("/**").permitAll()
               //  '/'와 '/api/auth' 경로는 모든 사용자에게 허용되고, 나머지 요청은 인증된 사용자에게만 허용
               .anyRequest().authenticated();

       // jwtAuthencationFilter 를 UsernamePasswordAuthenticationFilter 앞에 등록하고 필터 체인을 반환하는 역할
       // Spring Security 필터 체인에 사용자 정의 필터를 추가하여 JWT 인증을 처리하도록 구성
       httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

       logger.debug("Web Security Configuration Completed");
       return httpSecurity.build();
   }

   //BCryptPasswordEncoder Bean등록
   @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
}