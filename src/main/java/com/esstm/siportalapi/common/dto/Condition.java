package com.esstm.siportalapi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 검색 조건을 표현하는 DTO
 * field   : 검색 대상 필드명
 * value   : 비교할 값
 * operator: 연산자(eq, ne, like 등)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    private String field;
    private Object value;
    private String operator;
}
