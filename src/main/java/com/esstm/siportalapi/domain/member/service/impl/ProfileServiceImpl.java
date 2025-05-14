// src/main/java/com/esstm/siportalapi/domain/member/service/impl/ProfileServiceImpl.java
package com.esstm.siportalapi.domain.member.service.impl;

import com.esstm.siportalapi.common.exception.NotFoundException;
import com.esstm.siportalapi.domain.member.dto.ProfileRequest;
import com.esstm.siportalapi.domain.member.dto.ProfileResponse;
import com.esstm.siportalapi.domain.member.repository.ProfileMapper;
import com.esstm.siportalapi.domain.member.vo.ProfileVo;
import com.esstm.siportalapi.domain.member.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper mapper;

    @Override
    @Transactional
    public ProfileResponse createProfile(Integer memberId, ProfileRequest req) {
        ProfileVo vo = new ProfileVo(
                memberId,
                req.nickname(),
                req.avatarUrl(),
                req.bio(),
                req.preferences(),
                null, // createdAt은 DB DEFAULT 사용
                null
        );
        mapper.insert(vo);
        return map(mapper.findById(memberId));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponse getProfile(Integer memberId) {
        ProfileVo vo = mapper.findById(memberId);
        if (vo == null) {
            throw new NotFoundException("프로필을 찾을 수 없습니다: memberId=" + memberId);
        }
        return map(vo);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(Integer memberId, ProfileRequest req) {
        ProfileVo existing = mapper.findById(memberId);
        if (existing == null) {
            throw new NotFoundException("프로필을 찾을 수 없습니다: memberId=" + memberId);
        }
        ProfileVo vo = new ProfileVo(
                memberId,
                req.nickname(),
                req.avatarUrl(),
                req.bio(),
                req.preferences(),
                existing.createdAt(),
                null
        );
        mapper.update(vo);
        return map(mapper.findById(memberId));
    }

    private ProfileResponse map(ProfileVo vo) {
        return new ProfileResponse(
                vo.memberId(),
                vo.nickname(),
                vo.avatarUrl(),
                vo.bio(),
                vo.preferences(),
                vo.createdAt(),
                vo.updatedAt()
        );
    }
}
