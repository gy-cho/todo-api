package com.kbds.study.todo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

public class JwtTokenUtil {

    // JWT 서명용 비밀 키
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 유효기간 (예: 1시간)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; 

    /**
     * ✅ 사용자 이름을 기반으로 JWT 토큰 생성
     */
    public String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)                     // 사용자 정보
                .issuedAt(new Date())                  // 토큰 발급 시간
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(SECRET_KEY)                     // 서명 (비밀키 사용)
                .compact();                               // 토큰 생성 후 문자열로 반환
    }

    public String getUsernameFromToken(String token) {
      try {
          Claims claims = Jwts.parser()
              .verifyWith(SECRET_KEY)
              .build()
              .parseSignedClaims(token)
              .getPayload();
          return claims.getSubject();  // 사용자 이름이 'sub' 클레임에 저장되어 있다고 가정
      } catch (Exception e) {
          throw new RuntimeException("Invalid token", e);
      }
    }


}
