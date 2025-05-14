// src/main/java/com/esstm/siportalapi/auth/service/impl/AuthServiceImpl.java
package com.esstm.siportalapi.auth.service.impl;

import com.esstm.siportalapi.auth.dto.LoginResponse;
import com.esstm.siportalapi.auth.service.AuthService;
import com.esstm.siportalapi.common.model.Status;
import com.esstm.siportalapi.domain.member.model.Member;
import com.esstm.siportalapi.domain.member.repository.MemberMapper;
import com.esstm.siportalapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 인증 서비스 구현체
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(String email, String password) {
        // 1. 사용자 인증
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException ex) {
            throw new AuthenticationCredentialsNotFoundException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        // 2. 유저 조회 및 상태 검사
        Member member = memberMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 고객테이블에 Status 컬럼이 없음
//        if (member.getStatus() != Status.ACTIVE) {
//            throw new DisabledException("현재 사용 불가능한 계정입니다.");
//        }

        // 3. JWT 토큰 생성
        String accessToken  = jwtTokenProvider.createAccessToken(email, member.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        return new LoginResponse(accessToken, refreshToken);
    }
}
