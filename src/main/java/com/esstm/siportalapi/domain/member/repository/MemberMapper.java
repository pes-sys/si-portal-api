package com.esstm.siportalapi.domain.member.repository;

import com.esstm.siportalapi.domain.member.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    /** 검색 + 페이징용 (Condition 리스트, offset, limit) */
    List<MemberVo> search(Map<String, Object> params);

    /** 검색 조건에 맞는 전체 건수 조회 */
    long count(Map<String, Object> params);

    /** 단건 조회 (member_id) */
    MemberVo findById(Integer memberId);

    /** 단건 조회 (email) */
    MemberVo findByEmail(String email);

    /** 신규 회원 저장 */
    void insert(MemberVo vo);

    void update(MemberVo vo);

    void softDelete(Integer id);

    long countByEmail(String email);

    long nextVal();
}
