package com.esstm.siportalapi.domain.member.dto;

import jakarta.validation.constraints.Min;

public class PageRequest {
    @Min(0) private int page = 0;
    @Min(1) private int size = 10;
    private String sortBy;
    private Boolean desc = false;

    // getters/setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    public Boolean getDesc() { return desc; }
    public void setDesc(Boolean desc) { this.desc = desc; }
}
