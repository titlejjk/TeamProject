package com.project.filter;

import com.project.security.TokenProvider;
import com.project.user.dto.UserEnumClass.UserRole;  // UserRole import
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            logger.debug("Processing authentication for '{}'", request.getRequestURL());

            String token = parseBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) {

                String userEmail = tokenProvider.validate(token);

                String roleByToken = tokenProvider.getRoleByToken(token);

                // Convert the roles from String to UserRole enum
                List<GrantedAuthority> authorities = Arrays.stream(roleByToken.split(","))
                        .map(role -> new SimpleGrantedAuthority(UserRole.fromValue(role).getValue()))
                        .collect(Collectors.toList());

                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userEmail, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);

                logger.debug("Authenticated user '{}' with roles '{}'", userEmail, roleByToken);
            }
        } catch (Exception e) {
            logger.error("Failed to process authentication request", e);
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
