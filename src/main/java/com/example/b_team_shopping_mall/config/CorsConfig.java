package com.example.b_team_shopping_mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답할때 json을 자바스크립트에서 처리할 수 있게 할지 설정할 수 있는 것
        config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용한다.
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용한다.
        config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용한다.
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
