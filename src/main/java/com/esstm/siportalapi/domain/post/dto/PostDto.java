package com.esstm.siportalapi.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * PostDto(유저디티오)
 * 내부 전송용 DTO: PostVo 데이터를 Service 간 전달
 */
@Getter
@Setter
@AllArgsConstructor
public class PostDto {
    private int postId;     // CHAR(15)
    private int displayNo;
    private String memberId;   // CHAR(15) 작성자
    private String title;      // VARCHAR(200)
    private String content;    // TEXT
    private String status;     // ENUM: DRAFT, PUBLISHED, ARCHIVED
    private String createdAt;  // DATETIME (문자열 ISO)
    private String updatedAt;  // DATETIME (문자열 ISO)
}