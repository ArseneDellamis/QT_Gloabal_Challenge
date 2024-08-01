package com.example.blog.QT_Global_Blog.AuthenticeConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//during jwt authentication implement authentication filter first
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException
    {

        final String authHeader = request.getHeader(AuthConstants.HEADER_STRING);
        final String jwtToken;
        final String userEmail;
        if (authHeader == null || !authHeader
                .startsWith(AuthConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
//        extracting token form authheader
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);//todo extract userEmail from token;
    }
}
