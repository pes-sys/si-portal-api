// src/main/java/com/esstm/siportalapi/domain/post/vo/PostVo.java
package com.esstm.siportalapi.domain.post.vo;

import com.esstm.siportalapi.common.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostVo {
    private Long postId;
    private String title;
    private String content;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}