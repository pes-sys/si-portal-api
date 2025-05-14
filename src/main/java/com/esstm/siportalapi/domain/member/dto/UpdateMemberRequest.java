package com.esstm.siportalapi.domain.member.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * UpdateMemberRequest
 * 회원 정보 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMemberRequest {
    @Size(min = 1, message = "이름은 최소 1자 이상")
    private String name;
    private String phoneNumber;
    private String role;
}
