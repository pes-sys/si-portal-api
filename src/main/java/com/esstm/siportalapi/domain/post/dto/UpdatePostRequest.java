// src/main/java/com/esstm/siportalapi/domain/post/dto/UpdatePostRequest.java
package com.esstm.siportalapi.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}