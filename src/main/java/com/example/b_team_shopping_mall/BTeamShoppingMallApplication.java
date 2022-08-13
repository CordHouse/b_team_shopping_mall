package com.example.b_team_shopping_mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class) // security 일시적 제한
public class BTeamShoppingMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(BTeamShoppingMallApplication.class, args);
	}

}
