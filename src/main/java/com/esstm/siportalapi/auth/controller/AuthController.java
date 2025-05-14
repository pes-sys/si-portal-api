// src/main/java/com/esstm/siportalapi/auth/controller/AuthController.java
package com.esstm.siportalapi.auth.controller;

import com.esstm.siportalapi.auth.dto.LoginRequest;
import com.esstm.siportalapi.auth.dto.LoginResponse;
import com.esstm.siportalapi.auth.service.AuthService;
import com.esstm.siportalapi.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 인증(Authentication) REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 사용자 로그인
     *
     * @param req 이메일·비밀번호
     * @return 액세스 토큰 및 리프레시 토큰
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest req) {
        LoginResponse tokens = authService.login(req.getEmail(), req.getPassword());
        return ApiResponse.success(tokens);
    }
}
