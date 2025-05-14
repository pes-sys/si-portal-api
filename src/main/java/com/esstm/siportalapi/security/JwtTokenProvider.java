// src/main/java/com/esstm/siportalapi/security/JwtTokenProvider.java
package com.esstm.siportalapi.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * JWT 생성 및 검증 유틸리티
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") private String secretKey;
    @Value("${jwt.access.expire}") private long accessExpireMs;
    @Value("${jwt.refresh.expire}") private long refreshExpireMs;

    public String createAccessToken(String email, Collection<? extends GrantedAuthority> roles) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles.stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExpireMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpireMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 검증 메서드 생략…
}
