// src/main/java/com/esstm/siportalapi/domain/post/repository/PostMapper.java
package com.esstm.siportalapi.domain.post.repository;

import com.esstm.siportalapi.common.dto.Condition;
import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.domain.member.vo.MemberVo;
import com.esstm.siportalapi.domain.post.dto.PostResponse;
import com.esstm.siportalapi.domain.post.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    Long insert(PostVo vo);

    /** 검색 + 페이징용 (Condition 리스트, offset, limit) */
    List<PostVo> search(Map<String, Object> params);

    List<PostVo> selectAll(Map<String, Object> params);

    PostVo selectById(Long postId);

    int update(PostVo vo);

    int delete(Long postId);

    Long count(Map<String, Object> params);
}