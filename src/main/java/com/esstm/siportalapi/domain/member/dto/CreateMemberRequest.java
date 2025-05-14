package com.esstm.siportalapi.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * CreateMemberRequest
 * 회원 등록 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMemberRequest {
    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다")
    private String password;

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "유효한 이메일이어야 합니다")
    private String email;

    private String phoneNumber;

    @Builder.Default
    private String role = "MEMBER";
}
