package com.esstm.siportalapi.domain.member.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Member 도메인 모델
 * 비즈니스 로직(패스워드 암호화, 업데이트 등)을 포함
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // ← 이걸 추가
public class Member {
    private Integer memberId;
    private String name;
    private String password;         // 평문 비밀번호 (암호화 전)
    private String passwordHash;     // 암호화된 비밀번호
    private String email;
    private boolean emailVerified;
    private String phoneNumber;
    private String role;
    private Long points;
    private boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    /**
     * 비밀번호를 암호화하여 passwordHash에 저장하고, 원문 비밀번호는 null 처리
     */
    public void encryptPassword(PasswordEncoder encoder) {
        this.passwordHash = encoder.encode(this.password);
        this.password = null;
    }

    /**
     * 회원 정보 수정 (이름, 전화번호, 역할)
     */
    public void update(String name, String phoneNumber, String role) {
        if (name != null) this.name = name;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (role != null) this.role = role;
        this.updatedAt = LocalDateTime.now();
    }
}
