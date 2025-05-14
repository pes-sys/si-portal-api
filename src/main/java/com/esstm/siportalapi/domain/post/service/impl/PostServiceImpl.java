// src/main/java/com/esstm/siportalapi/domain/post/service/impl/PostServiceImpl.java
package com.esstm.siportalapi.domain.post.service.impl;

import com.esstm.siportalapi.common.dto.PageInfo;
import com.esstm.siportalapi.common.dto.PageResponse;
import com.esstm.siportalapi.common.exception.NotFoundException;
import com.esstm.siportalapi.common.model.Status;
import com.esstm.siportalapi.domain.post.dto.CreatePostRequest;
import com.esstm.siportalapi.domain.post.dto.PostRequest;
import com.esstm.siportalapi.domain.post.dto.PostResponse;
import com.esstm.siportalapi.domain.post.dto.UpdatePostRequest;
import com.esstm.siportalapi.domain.post.mapper.PostMapStruct;
import com.esstm.siportalapi.domain.post.repository.PostMapper;
import com.esstm.siportalapi.domain.post.service.PostService;
import com.esstm.siportalapi.domain.post.vo.PostVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostMapStruct mapStruct;

    @Override
    @Transactional
    public PostResponse create(CreatePostRequest request) {
        PostVo vo = new PostVo();
        vo.setTitle(request.getTitle());
        vo.setContent(request.getContent());
        vo.setStatus(Status.ACTIVE);
        postMapper.insert(vo);
        return getById(vo.getPostId());
    }

    @Override
    public PageResponse<PostResponse> search(PostRequest request) {
        PageInfo pi = request.getPageInfo();
        if (pi == null) pi = new PageInfo(0, 10, 0, 0);

        Map<String, Object> params = Map.of(
                "conditions", request.getConditions(),
                "offset", pi.getCurrentPage() * pi.getPageSize(),
                "limit", pi.getPageSize()
        );

        List<PostVo> vos = postMapper.search(params);
        long total = postMapper.count(params);

        List<PostResponse> items = vos.stream()
                .map(mapStruct::toResponse)
                .collect(Collectors.toList());

        return  PageResponse.<PostResponse>builder()
                .items(items)
                .totalElements(total)
                .totalPages((int) ((total + pi.getPageSize() - 1) / pi.getPageSize()))
                .currentPage(pi.getCurrentPage())
                .build();
    }

    private com.esstm.siportalapi.domain.post.model.Post voToEntity(PostVo vo) {
        return com.esstm.siportalapi.domain.post.model.Post.builder()
                .title(vo.getTitle())
                .content(vo.getContent())
                .status(vo.getStatus())
                .build();
    }

    @Override
    public PostResponse getById(Long id) {
        PostVo vo = postMapper.selectById(id);
        if (vo == null) throw new NotFoundException("Post not found: " + id);
        return mapStruct.toResponse(vo);
    }

    @Override
    @Transactional
    public PostResponse update(Long id, UpdatePostRequest request) {
        PostVo existing = postMapper.selectById(id);
        if (existing == null) throw new NotFoundException("Post not found: " + id);
        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        postMapper.update(existing);
        return getById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        postMapper.delete(id);
    }
}
