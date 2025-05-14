package com.esstm.siportalapi.domain.member.service.impl;

import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.common.dto.PageInfo;
import com.esstm.siportalapi.domain.member.dto.CreateMemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberResponse;
import com.esstm.siportalapi.domain.member.dto.MemberRequest;
import com.esstm.siportalapi.domain.member.dto.UpdateMemberRequest;
import com.esstm.siportalapi.domain.member.repository.MemberMapper;
import com.esstm.siportalapi.domain.member.mapper.MemberMapStruct;
import com.esstm.siportalapi.domain.member.service.MemberService;
import com.esstm.siportalapi.domain.member.vo.MemberVo;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final MemberMapStruct mapStruct;
    private final PasswordEncoder passwordEncoder;

    /** 회원가입 */
    @Override
    @Transactional
    public MemberResponse register(CreateMemberRequest request) {
        // 이메일 중복 확인
        if (memberMapper.countByEmail(request.getEmail()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다");
        }
        // DTO → Domain
        var member = mapStruct.toModel(request);
        member.encryptPassword(passwordEncoder);

        // ID 할당
        long seq = memberMapper.nextVal();
        member.setMemberId((int) seq);

        // Domain → VO
        MemberVo vo = mapStruct.toVo(member);
        memberMapper.insert(vo);

        // 이 시점에 vo.memberId에 AUTO_INCREMENT 값이 채워져 있습니다.
        Integer newId = vo.getMemberId();

        // 이후 재조회
        MemberVo saved = memberMapper.findById(newId);
        // VO → Response DTO
        return mapStruct.toResponse(saved);
    }

    /** 검색 + 페이징 */
    @Override
    public PageResponse<MemberResponse> search(MemberRequest request) {
        PageInfo pi = request.getPageInfo();
        if (pi == null) pi = new PageInfo(0, 10, 0, 0);

        Map<String, Object> params = Map.of(
                "conditions", request.getConditions(),
                "offset", pi.getCurrentPage() * pi.getPageSize(),
                "limit", pi.getPageSize()
        );
        List<MemberVo> vos = memberMapper.search(params);
        long total = memberMapper.count(params);

        List<MemberResponse> items = vos.stream()
                .map(mapStruct::toResponse)
                .collect(Collectors.toList());

        return PageResponse.<MemberResponse>builder()
                .items(items)
                .totalElements(total)
                .totalPages((int) ((total + pi.getPageSize() - 1) / pi.getPageSize()))
                .currentPage(pi.getCurrentPage())
                .build();
    }

    /** 단건 조회 by ID */
    @Override
    @Cacheable(value = "members", key = "#id")
    public MemberResponse findById(Integer id) {
        MemberVo vo = memberMapper.findById(id);
        if (vo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found: " + id);
        return mapStruct.toResponse(vo);
    }

    /** 회원 정보 수정 */
    @Override
    @Transactional
    public MemberResponse update(Integer id, UpdateMemberRequest request) {
        MemberVo existing = memberMapper.findById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found: " + id);

        // VO → Domain → 수정 로직 → VO
        var member = mapStruct.toModel(existing);
        member.update(request.getName(), request.getPhoneNumber(), request.getRole());
        MemberVo vo = mapStruct.toVo(member);
        memberMapper.update(vo);

        return mapStruct.toResponse(vo);
    }

    /** 회원 삭제 (소프트) */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        MemberVo vo = memberMapper.findById(id);
        if (vo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found: " + id);
        memberMapper.softDelete(id);
    }
}