// src/main/java/com/esstm/siportalapi/common/dto/ApiResponse.java
package com.esstm.siportalapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String customerMessage;
    private String errorMessage;
    private T data;

    /** 성공 응답 (데이터만) */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, null, data);
    }

    /** 성공 응답 (메시지 + 데이터) */
    public static <T> ApiResponse<T> success(String customerMessage, T data) {
        return new ApiResponse<>(true, customerMessage, null, data);
    }

    /** 실패 응답 (에러 메시지만) */
    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>(false, null, errorMessage, null);
    }

    /** 실패 응답 (메시지 + 에러 메시지) */
    public static <T> ApiResponse<T> error(String customerMessage, String errorMessage) {
        return new ApiResponse<>(false, customerMessage, errorMessage, null);
    }



}
