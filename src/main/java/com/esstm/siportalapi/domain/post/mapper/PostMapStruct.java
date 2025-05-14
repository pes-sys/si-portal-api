// src/main/java/com/esstm/siportalapi/domain/post/mapper/PostMapStruct.java
package com.esstm.siportalapi.domain.post.mapper;

import com.esstm.siportalapi.domain.post.dto.*;
import com.esstm.siportalapi.domain.post.model.Post;
import com.esstm.siportalapi.domain.post.vo.PostVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapStruct {

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "dto.title", target = "title")
    void updateEntity(UpdatePostRequest dto, @MappingTarget Post post);

    /** VO -> MemberResponse DTO */
    PostResponse toResponse(PostVo post);
}