// src/main/java/com/esstm/siportalapi/auth/service/impl/AuthServiceImpl.java
package com.esstm.siportalapi.auth.service.impl;

import com.esstm.siportalapi.auth.dto.LoginResponse;
import com.esstm.siportalapi.auth.service.AuthService;
import com.esstm.siportalapi.domain.member.vo.MemberVo;
import com.esstm.siportalapi.domain.member.repository.MemberMapper;
import com.esstm.siportalapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(String email, String password) {
        // 1) 인증 시도
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // 2) 사용자 조회
        MemberVo vo = memberMapper.findByEmail(email);
        if (vo == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // 3) 권한(SimpleGrantedAuthority) 리스트 생성
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(vo.getRole()));

        // 4) JWT 토큰 생성
        String accessToken  = jwtTokenProvider.createAccessToken(email, authorities);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        return new LoginResponse(accessToken, refreshToken);
    }
}
