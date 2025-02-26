package com.kbds.study.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 대해 CORS 설정
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")  // 허용할 출처
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");  // 허용할 HTTP 메서드
	}

}