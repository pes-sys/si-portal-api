// src/main/java/com/esstm/siportalapi/domain/post/dto/PostRequest.java
package com.esstm.siportalapi.domain.post.dto;

import com.esstm.siportalapi.common.dto.Condition;
import com.esstm.siportalapi.common.dto.PageInfo;
import com.esstm.siportalapi.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequest extends PageRequest {
    private List<Condition> conditions;
    private PageInfo pageInfo;
}