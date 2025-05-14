// src/main/java/com/esstm/siportalapi/domain/post/controller/PostController.java
package com.esstm.siportalapi.domain.post.controller;

import com.esstm.siportalapi.common.dto.ApiResponse;
import com.esstm.siportalapi.common.dto.Condition;
import com.esstm.siportalapi.common.dto.PageInfo;
import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.domain.post.dto.CreatePostRequest;
import com.esstm.siportalapi.domain.post.dto.PostRequest;
import com.esstm.siportalapi.domain.post.dto.PostResponse;
import com.esstm.siportalapi.domain.post.dto.UpdatePostRequest;
import com.esstm.siportalapi.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글(Post) 관련 REST API 컨트롤러
 *
 * - Create: POST   /api/posts
 * - Search: POST   /api/posts/search
 * - Read One: GET  /api/posts/{id}
 * - Update: PUT    /api/posts/{id}
 * - Delete: DELETE /api/posts/{id}
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 등록
     *
     * @param request 등록할 게시글 정보
     * @return 생성된 게시글 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostResponse> createPost(
            @Valid @RequestBody CreatePostRequest request) {
        PostResponse response = postService.create(request);
        return ApiResponse.success(response);
    }

    /**
     * 게시글 검색 + 페이징 조회
     *
     * @param request 검색 조건 리스트 + pageInfo.currentPage, pageInfo.pageSize
     * @return 페이징된 게시글 리스트
     */
    @PostMapping("/search")
    public ApiResponse<PageResponse<PostResponse>> searchPosts(
            @Valid @RequestBody PostRequest request) {
        PageResponse<PostResponse> result = postService.search(request);
        return ApiResponse.success(result);
    }

    /**
     * 단일 게시글 조회
     *
     * @param id 게시글 고유 ID
     * @return 조회된 게시글 정보
     */
    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPostById(
            @PathVariable Long id) {
        return ApiResponse.success(
                postService.getById(id)
        );
    }

    /**
     * 게시글 수정
     *
     * @param id      수정할 게시글 ID
     * @param request 수정할 정보 (제목, 내용, 상태 optional)
     * @return 수정된 게시글 정보
     */
    @PutMapping("/{id}")
    public ApiResponse<PostResponse> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePostRequest request) {
        return ApiResponse.success(
                postService.update(id, request)
        );
    }

    /**
     * 게시글 삭제 (비활성화)
     *
     * @param id 삭제할 게시글 ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deletePost(
            @PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.success((Void) null);
    }
}
