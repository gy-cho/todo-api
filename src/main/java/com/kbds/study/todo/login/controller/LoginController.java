package com.kbds.study.todo.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.kbds.study.todo.config.JwtTokenUtil;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public Map<String, String> login(@RequestParam String userId) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = jwtTokenUtil.generateToken(userId);

        // JSON 형태로 토큰 반환
        Map<String, String> response = new HashMap<>();
        response.put("jwtToken", token);
        return response;
    }
}
