package com.esstm.siportalapi.domain.member.controller;

import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.domain.member.dto.CreateMemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberResponse;
import com.esstm.siportalapi.domain.member.dto.UpdateMemberRequest;
import com.esstm.siportalapi.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Member CRUD operations
 */
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 등록
     */
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(
            @RequestBody @Valid CreateMemberRequest request) {
        MemberResponse response = memberService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 회원 목록 조회 (검색 + 페이징)
     */
    @PostMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResponse<MemberResponse>> searchMembers(
            @RequestBody @Valid MemberRequest request) {
        PageResponse<MemberResponse> page = memberService.search(request);
        return ResponseEntity.ok(page);
    }

    /**
     * 회원 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(
            @PathVariable Integer id) {
        MemberResponse response = memberService.findById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원 정보 수정
     */
    @PutMapping("/{id}")
    @PreAuthorize("#id == principal.memberId or hasRole('ADMIN')")
    public ResponseEntity<MemberResponse> updateMember(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateMemberRequest request) {
        MemberResponse updated = memberService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * 회원 삭제 (소프트 삭제)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("#id == principal.memberId or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteById(id);
    }
}
