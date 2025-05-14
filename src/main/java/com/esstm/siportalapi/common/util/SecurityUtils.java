package com.esstm.siportalapi.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityUtils(시큐리티유틸스)
 * 보안 관련 공통 메서드를 제공하는 유틸리티 클래스
 */
public class SecurityUtils {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 주어진 원시 비밀번호(rawPassword)를 암호화하여 반환
     */
    public static String encodePassword(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    /**
     * 원시 비밀번호와 암호화된 비밀번호가 일치하는지 검증
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}