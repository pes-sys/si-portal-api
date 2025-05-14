package com.esstm.siportalapi.domain.member.dto;

import com.esstm.siportalapi.common.dto.Condition;
import com.esstm.siportalapi.common.dto.PageInfo;
import lombok.*;

import java.util.List;

/**
 * MemberRequest(유저리퀘스트)
 * 검색 조건 및 페이징 정보 바인딩용 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberRequest {
    private List<Condition> conditions;
    private PageInfo pageInfo;
}