package com.project.filter;

import com.project.security.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthencationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    // 로거 인스턴스
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthencationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            logger.debug("Processing authentication for '{}'", request.getRequestURL()); // 로깅 추가
            //호출 HTTP Header에 토큰이 있는지 확인
            String token = parseBearerToken(request);

            //해당 토큰이 null이 아니라면
            if (token != null && !token.equalsIgnoreCase("null")) {

                //토큰을 검사하고 이상이 없다면 userEmail을 가져온다.
                String userEmail = tokenProvider.validate(token);

                //TODO: 토큰 안에 있는 권한을 가져온다.
                String roleByToken = tokenProvider.getRoleByToken(token);

                //TODO: Spring Security에 권한 부여
                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                logger.debug("Authenticated user '{}'", userEmail); // 로깅 추가
                logger.debug("SecurityContext created with authentication: '{}'", securityContext.getAuthentication());
            }
        }catch (Exception e){
            logger.error("Failed to process authentication request", e); //로깅추가
        }

            filterChain.doFilter(request, response);
        }

    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return null;
        }
        return bearerToken.substring(7);
    }
}
