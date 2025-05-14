package com.esstm.siportalapi.common.dto;

import lombok.*;

/**
 * PageInfo(페이지인포)
 * 페이징 정보 DTO
 * currentPage   : 현재 페이지 번호 (1부터 시작)
 * pageSize      : 페이지당 요소 수
 * totalPages    : 전체 페이지 수
 * totalElements : 전체 요소 수
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfo {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}