// src/main/java/com/esstm/siportalapi/domain/post/model/Post.java
package com.esstm.siportalapi.domain.post.model;

import com.esstm.siportalapi.common.model.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // enum 타입 필드에 직접 문자열이 아닌, enum 상수를 사용합니다.
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;  // <-- enum 상수로 초기화


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Post(String title, String content, Status status) {
        this.title = title;
        this.content = content;
        this.status = (status != null ? status : Status.ACTIVE);;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}