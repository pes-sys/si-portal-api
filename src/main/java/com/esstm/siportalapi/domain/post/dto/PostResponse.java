// src/main/java/com/esstm/siportalapi/domain/post/dto/PostResponse.java
package com.esstm.siportalapi.domain.post.dto;

import lombok.Getter;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}