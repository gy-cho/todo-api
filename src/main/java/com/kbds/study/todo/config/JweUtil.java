package com.kbds.study.todo.config;

import com.kbds.study.todo.login.model.UserDto;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JweUtil {

    private static final SecretKey SECRET_KEY = generateSecretKey();

    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Key 생성 실패", e);
        }
    }

    // JWE 토큰 생성
    public String generateJweToken(UserDto userDto) throws Exception {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(String.valueOf(userDto.getUserId()))
                .claim("loginId", userDto.getLoginId())
                .claim("userName", userDto.getUserName())
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000)) // 1시간 유효
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claimsSet
        );
        signedJWT.sign(new MACSigner(SECRET_KEY.getEncoded()));

        JWEObject jweObject = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
                        .contentType("JWT")
                        .build(),
                new Payload(signedJWT)
        );

        jweObject.encrypt(new DirectEncrypter(SECRET_KEY.getEncoded()));
        return jweObject.serialize();
    }

    // JWE 토큰 복호화
    public UserDto getUserIdFromJweToken(String token) throws Exception {
        JWEObject jweObject = JWEObject.parse(token);
        jweObject.decrypt(new DirectDecrypter(SECRET_KEY.getEncoded()));

        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
        if (signedJWT == null || !signedJWT.verify(new MACVerifier(SECRET_KEY.getEncoded()))) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        // 각 클레임 추출
        String userIdStr = claims.getStringClaim("userId");  // userId는 문자열로 전달된다고 가정
        long userId = Long.parseLong(userIdStr);  // 문자열을 long으로 변환
        String loginId = claims.getSubject();  // subject에 로그인 ID가 저장되어 있다면
        String userName = claims.getStringClaim("userName");

        // UserDto 객체 생성
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setLoginId(loginId);
        userDto.setUserName(userName);

        return userDto;
    }
}
