package com.example.b_team_shopping_mall.config.jwt;

import com.example.b_team_shopping_mall.entity.Register;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "lsienskasjcjaslcfjahsdlaseasdqfxsvbxbetrhwfgsxczvwgssdfsdfa";

    // Token 유효시간 10분 설정
    private long tokenValidTime = 10 * 60 * 1000L;
    private final UserDetailsService userDetailsService;
//    private final CustomUserDetailService customUserDetailService;

    // 객체 초기화, secretKey 를 Base64로 인코딩한다.
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 생성 ( JWT )
    public String createToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
        Claims claims = Jwts.claims().setSubject(authentication.getName()); // JWT Payload에 저장되는 정보단위
        claims.put("role", authentication.getAuthorities());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .claim("auth", authorities)
                .setExpiration(new Date(now.getTime()+tokenValidTime)) // 유효시간 설정 현재시간 + 유효시간 10분
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘, secret 값 셋팅
                .compact();
    }

    // JWT Token 에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Token에서 회원 정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 Token을 가져온다. Header : Authorization 인 경우만
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    // Token 유효시간 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }
}
