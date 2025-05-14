// src/main/java/com/esstm/siportalapi/auth/dto/LoginResponse.java
package com.esstm.siportalapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 응답 DTO: JWT 액세스·리프레시 토큰 포함
 */
@Getter
@AllArgsConstructor
public class LoginResponse {
    private final String accessToken;
    private final String refreshToken;
}
