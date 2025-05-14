// src/main/java/com/esstm/siportalapi/common/dto/ErrorResponse.java
package com.esstm.siportalapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통 에러 응답 DTO
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
}
