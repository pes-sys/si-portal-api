// src/main/java/com/esstm/siportalapi/auth/service/AuthService.java
package com.esstm.siportalapi.auth.service;

import com.esstm.siportalapi.auth.dto.LoginResponse;

/**
 * 인증 서비스 인터페이스
 */
public interface AuthService {
    /**
     * 사용자 인증 후 JWT 발급
     *
     * @param email    로그인 이메일
     * @param password 로그인 비밀번호
     * @return 발급된 액세스·리프레시 토큰
     */
    LoginResponse login(String email, String password);
}
