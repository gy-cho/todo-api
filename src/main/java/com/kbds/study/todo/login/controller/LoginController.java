package com.kbds.study.todo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.kbds.study.todo.config.JweUtil;
import com.kbds.study.todo.login.model.UserDto;
import com.kbds.study.todo.login.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private final JweUtil jweUtil;
    @Autowired
    private final LoginService loginService;
    
    public LoginController(JweUtil jweUtil, LoginService loginService) {
        this.jweUtil = jweUtil;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public Map<String, String> login(@RequestParam String loginId) {
        UserDto result = loginService.findByUserId(loginId);
        try {
            // JWE 토큰 생성
            String token = jweUtil.generateJweToken(result);

            // JSON 형태로 토큰 반환
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } catch (Exception e) {
            // 예외 처리
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "토큰 생성 실패: " + e.getMessage());
            return errorResponse;
        }
    }
}
