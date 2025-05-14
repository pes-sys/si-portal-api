// src/main/java/com/esstm/siportalapi/domain/member/service/ProfileService.java
package com.esstm.siportalapi.domain.member.service;

import com.esstm.siportalapi.domain.member.dto.ProfileRequest;
import com.esstm.siportalapi.domain.member.dto.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(Integer memberId, ProfileRequest request);
    ProfileResponse getProfile(Integer memberId);
    ProfileResponse updateProfile(Integer memberId, ProfileRequest request);
}
