// src/main/java/com/esstm/siportalapi/domain/post/dto/CreatePostRequest.java
package com.esstm.siportalapi.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CreatePostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
