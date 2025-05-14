// src/main/java/com/esstm/siportalapi/domain/member/dto/ProfileRequest.java
package com.esstm.siportalapi.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfileRequest(
        @NotNull(message = "nickname은 필수입니다")
        @Size(max = 50, message = "nickname은 최대 50자까지 허용됩니다")
        String nickname,

        @Size(max = 255, message = "avatarUrl은 최대 255자까지 허용됩니다")
        String avatarUrl,

        @Size(max = 500, message = "bio는 최대 500자까지 허용됩니다")
        String bio,

        String preferences
) {}
