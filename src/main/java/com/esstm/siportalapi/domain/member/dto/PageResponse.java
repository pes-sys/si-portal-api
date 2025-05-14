package com.esstm.siportalapi.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private List<T> items;
    private long totalElements;
    private int totalPages;
    private int currentPage;
}
