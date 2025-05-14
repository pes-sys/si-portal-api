package com.esstm.siportalapi.domain.member.service;

import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.domain.member.dto.CreateMemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberResponse;
import com.esstm.siportalapi.domain.member.dto.UpdateMemberRequest;
import com.esstm.siportalapi.domain.member.model.Member;

public interface MemberService {

     /** 회원가입 */
    MemberResponse register(CreateMemberRequest request);

    /** 검색 + 페이징 */
    PageResponse<MemberResponse> search(MemberRequest request);

    /** 단건 조회 by ID */
    MemberResponse findById(Integer id);

    /** 회원 정보 수정 */
    MemberResponse update(Integer id, UpdateMemberRequest request);

    /** 회원 삭제 (소프트) */
    void deleteById(Integer id);
}
