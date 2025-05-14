package com.esstm.siportalapi.domain.member.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo {

    private Integer memberId;
    private String name;
    private String passwordHash;       // DB 의 password_hash
    private String email;
    private String phoneNumber;
    private String role;
    private long points;               // DB 의 points
    private boolean emailVerified;     // DB 의 is_email_verified
    private boolean deleted;           // DB 의 is_deleted
    private LocalDateTime deletedAt;   // DB 의 deleted_at
    private LocalDateTime createdAt;   // DB 의 created_at
    private LocalDateTime updatedAt;   // DB 의 updated_at

}
