//package com.example.b_team_shopping_mall.config;
//
//import com.example.b_team_shopping_mall.config.jwt.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class HeaderResponseFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
////        if(req.getMethod().equals("POST"))
////            res.addHeader("Authorization");
//
//        if(req.getMethod().equals("GET")) {
//            String authHeader = req.getHeader("Authorization");
//            if(authHeader.equals("cos")) {
//                chain.doFilter(req, res);
//            }else{
//                System.out.println("인증 실패");
//            }
//        }
//
//    }
//}
