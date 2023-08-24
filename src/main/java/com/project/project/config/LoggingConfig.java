package com.project.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true); // 쿼리 문자열 포함
        filter.setIncludeClientInfo(true); // 클라이언트 정보 (예: IP 주소) 포함
        filter.setIncludeHeaders(true); // 헤더 포함
        filter.setIncludePayload(true); // 요청 본문 (Payload) 포함
        filter.setMaxPayloadLength(10000); // 본문 로깅 길이 제한 (필요에 따라 조정)
        filter.setAfterMessagePrefix("REQUEST DATA: "); // 로그 메시지 접두사
        return filter;
    }
}
