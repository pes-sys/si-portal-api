package com.esstm.siportalapi.domain.member.mapper;

import com.esstm.siportalapi.domain.member.dto.CreateMemberRequest;
import com.esstm.siportalapi.domain.member.dto.MemberResponse;
import com.esstm.siportalapi.domain.member.model.Member;
import com.esstm.siportalapi.domain.member.vo.MemberVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T22:44:29+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class MemberMapStructImpl implements MemberMapStruct {

    @Override
    public Member toModel(CreateMemberRequest request) {
        if ( request == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.name( request.getName() );
        member.password( request.getPassword() );
        member.email( request.getEmail() );
        member.phoneNumber( request.getPhoneNumber() );
        member.role( request.getRole() );

        return member.build();
    }

    @Override
    public Member toModel(MemberVo vo) {
        if ( vo == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.memberId( vo.getMemberId() );
        member.name( vo.getName() );
        member.passwordHash( vo.getPasswordHash() );
        member.email( vo.getEmail() );
        member.emailVerified( vo.isEmailVerified() );
        member.phoneNumber( vo.getPhoneNumber() );
        member.role( vo.getRole() );
        member.points( vo.getPoints() );
        member.deleted( vo.isDeleted() );
        member.createdAt( vo.getCreatedAt() );
        member.updatedAt( vo.getUpdatedAt() );
        member.deletedAt( vo.getDeletedAt() );

        return member.build();
    }

    @Override
    public MemberVo toVo(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberVo memberVo = new MemberVo();

        memberVo.setPasswordHash( member.getPasswordHash() );
        memberVo.setMemberId( member.getMemberId() );
        memberVo.setName( member.getName() );
        memberVo.setEmail( member.getEmail() );
        memberVo.setPhoneNumber( member.getPhoneNumber() );
        memberVo.setRole( member.getRole() );
        memberVo.setDeletedAt( member.getDeletedAt() );
        memberVo.setCreatedAt( member.getCreatedAt() );
        memberVo.setUpdatedAt( member.getUpdatedAt() );

        memberVo.setPoints( 0L );
        memberVo.setEmailVerified( false );
        memberVo.setDeleted( false );

        return memberVo;
    }

    @Override
    public MemberResponse toResponse(MemberVo vo) {
        if ( vo == null ) {
            return null;
        }

        MemberResponse.MemberResponseBuilder memberResponse = MemberResponse.builder();

        memberResponse.memberId( vo.getMemberId() );
        memberResponse.name( vo.getName() );
        memberResponse.email( vo.getEmail() );
        memberResponse.emailVerified( vo.isEmailVerified() );
        memberResponse.phoneNumber( vo.getPhoneNumber() );
        memberResponse.role( vo.getRole() );
        memberResponse.createdAt( vo.getCreatedAt() );
        memberResponse.updatedAt( vo.getUpdatedAt() );

        return memberResponse.build();
    }
}
