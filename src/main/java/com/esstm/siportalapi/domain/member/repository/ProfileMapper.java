package com.esstm.siportalapi.domain.member.repository;

import com.esstm.siportalapi.domain.member.vo.ProfileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface ProfileMapper {

    /** 목록 조회 (검색 + 페이징) */
    List<ProfileVo> search(Map<String, Object> params);

    /** 단건 조회 (String id) */
    ProfileVo findById(Integer id);

    /** 전체 개수 조회 (검색조건) */
    long count(Map<String, Object> params);

    /** 프로필 생성 */
    void insert(ProfileVo vo);

    /** 프로필 수정 */
    void update(ProfileVo vo);

    /** 프로필 소프트 삭제 */
    void softDelete(String id);

    /** 다음 시퀀스 값 */
    long nextVal();
}
