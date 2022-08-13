package com.example.b_team_shopping_mall.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에 JWT를 받아온다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;

        // 유효한 토큰인지 확인한다. ( 토큰이 null이 아니고, 유효시간이 남아있다면 )
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 유효한 토큰 정보를 받아온다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext에 Authentication 객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 헤더에 JWT를 받아온다.
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//
////        HttpServletRequest req = (HttpServletRequest) request;
////        HttpServletResponse res = (HttpServletResponse) response;
//
//        // 유효한 토큰인지 확인한다. ( 토큰이 null이 아니고, 유효시간이 남아있다면 )
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            // 유효한 토큰 정보를 받아온다.
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            // SecurityContext에 Authentication 객체를 저장한다.
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        chain.doFilter(request, response);
//    }
}

