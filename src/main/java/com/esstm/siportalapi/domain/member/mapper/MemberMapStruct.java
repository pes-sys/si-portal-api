package com.esstm.siportalapi.domain.member.mapper;

import com.esstm.siportalapi.domain.member.dto.CreateMemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberResponse;
import com.esstm.siportalapi.domain.member.model.Member;
import com.esstm.siportalapi.domain.member.vo.MemberVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct 매퍼: DTO, Domain, VO 간 변환
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapStruct {

    /** CreateMemberRequest -> Domain */
    Member toModel(CreateMemberRequest request);

    /** VO -> Domain (수정용) */
    Member toModel(MemberVo vo);

    /** Domain -> VO */
    @Mapping(source = "passwordHash", target = "passwordHash")
    @Mapping(target = "points", constant = "0L")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "deleted", constant = "false")
    MemberVo toVo(Member member);

    /** VO -> MemberResponse DTO */
    MemberResponse toResponse(MemberVo vo);
}
