package com.esstm.siportalapi.common.dto;

import lombok.*;
import java.util.List;

/**
 * PageResponse
 * 제네릭 페이징 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> items;
    private long totalElements;
    private int totalPages;
    private int currentPage;
}
