// src/main/java/com/esstm/siportalapi/domain/member/dto/ProfileResponse.java
package com.esstm.siportalapi.domain.member.dto;

import java.time.LocalDateTime;

public record ProfileResponse(
        Integer memberId,
        String nickname,
        String avatarUrl,
        String bio,
        String preferences,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
