package com.kbds.study.todo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.kbds.study.todo")
@ComponentScan(basePackages = "com.kbds.study.todo")
@MapperScan("com.kbds.study.todo.*.repository") // Mapper 인터페이스 자동 스캔
public class TodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
