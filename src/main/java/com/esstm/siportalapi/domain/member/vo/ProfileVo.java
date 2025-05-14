// src/main/java/com/esstm/siportalapi/domain/member/vo/ProfileVo.java
package com.esstm.siportalapi.domain.member.vo;

import java.time.LocalDateTime;

public record ProfileVo(
        Integer memberId,
        String nickname,
        String avatarUrl,
        String bio,
        String preferences,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
