package com.br.sgmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SgmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgmApiApplication.class, args);
		
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration corsconfiguration = new CorsConfiguration();
		corsconfiguration.addAllowedHeader("*");
		corsconfiguration.addAllowedMethod("*");
		corsconfiguration.addAllowedOrigin("*");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsconfiguration);
		return source;
	}

}
