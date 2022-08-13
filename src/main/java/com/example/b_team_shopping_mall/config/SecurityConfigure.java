package com.example.b_team_shopping_mall.config;
import com.example.b_team_shopping_mall.config.jwt.JwtAuthenticationFilter;
import com.example.b_team_shopping_mall.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private final CorsFilter corsFilter; // @Bean 설정이 되어 있어서 이렇게 가져올 수 있다.
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), SecurityContextHolderFilter.class);
        // 여기서부터는 시큐리티 + JWT 사용시 필수 작성
        http.csrf().disable();

        // 세션을 사용하지 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
//        .addFilter(corsFilter) // @CrossOrigin(인증x), 인증이 있을 때 -> 시큐리티 필터에 등록 인증 (o)

        .formLogin().disable() // formTag Login을 안한다.
        .httpBasic().disable() // https -> 보안, 우리는 http이기 때문에 httpBasic을 disable한다.

//        .addFilter(new HeaderResponseFilter())
                // Bearer -> http 방식, 토큰에 대한 유효시간 존재
                // 여기서부터는 개발하고자 하는 웹에 맞춰 작성

        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/auth").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//        .antMatchers("/api/auth").access("hasRole('ROLE_USER')")
//        .antMatchers("/api/auth/sign-up").hasRole("USER")
//        .antMatchers("/api/auth/login").hasRole("USER")
        .anyRequest().permitAll();
    }
}
