package com.esstm.siportalapi.domain.member.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * MemberResponse
 * 회원 단건/목록 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Integer memberId;
    private String name;
    private String email;
    private Boolean emailVerified;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
