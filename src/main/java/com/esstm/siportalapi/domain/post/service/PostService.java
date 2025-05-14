// src/main/java/com/esstm/siportalapi/domain/post/service/PostService.java
package com.esstm.siportalapi.domain.post.service;

import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.domain.post.dto.*;
import org.springframework.data.domain.Pageable;

public interface PostService {

    /** 게시판 등록 */
    PostResponse create(CreatePostRequest request);

    /** 검색 + 페이징 */
    PageResponse<PostResponse> search(PostRequest request);

    /** 단건 조회 */
    PostResponse getById(Long id);

    /** 게시판 수정 */
    PostResponse update(Long id, UpdatePostRequest request);

    /** 게시판 삭제 */
    void delete(Long id);
}
