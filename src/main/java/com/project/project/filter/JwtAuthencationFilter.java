package com.project.project.filter;

import com.project.project.security.TokenProvider;
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
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

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

            String token = parseBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) {
                String userEmail = tokenProvider.validate(token);
                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                logger.debug("Authenticated user '{}'", userEmail); // 로깅 추가
                logger.debug("SecurityContext created with authentication: '{}'", securityContext.getAuthentication());
            }
        } catch (Exception e) {
            logger.error("Failed to process authentication request", e); // 로깅 추가
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
