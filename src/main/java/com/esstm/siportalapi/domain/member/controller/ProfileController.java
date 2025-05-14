// src/main/java/com/esstm/siportalapi/domain/member/controller/ProfileController.java
package com.esstm.siportalapi.domain.member.controller;

import com.esstm.siportalapi.common.dto.ApiResponse;
import com.esstm.siportalapi.domain.member.dto.ProfileRequest;
import com.esstm.siportalapi.domain.member.dto.ProfileResponse;
import com.esstm.siportalapi.domain.member.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/{memberId}/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProfileResponse> create(
            @PathVariable Integer memberId,
            @RequestBody @Valid ProfileRequest request) {
        return ApiResponse.success(service.createProfile(memberId, request));
    }

    @GetMapping
    public ApiResponse<ProfileResponse> get(@PathVariable Integer memberId) {
        return ApiResponse.success(service.getProfile(memberId));
    }

    @PutMapping
    public ApiResponse<ProfileResponse> update(
            @PathVariable Integer memberId,
            @RequestBody @Valid ProfileRequest request) {
        return ApiResponse.success(service.updateProfile(memberId, request));
    }
}
