// src/main/java/com/esstm/siportalapi/common/dto/PageRequest.java
package com.esstm.siportalapi.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 요청 DTO: 페이지 번호 및 사이즈 정보를 포함
 */
@Getter
@Setter
public class PageRequest {
    /**
     * 요청할 페이지 번호 (0부터 시작)
     */
    private int page = 0;

    /**
     * 한 페이지당 조회할 데이터 수
     */
    private int size = 10;
}
