package com.esstm.siportalapi.domain.post.mapper;

import com.esstm.siportalapi.domain.post.dto.PostResponse;
import com.esstm.siportalapi.domain.post.dto.UpdatePostRequest;
import com.esstm.siportalapi.domain.post.model.Post;
import com.esstm.siportalapi.domain.post.vo.PostVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T22:44:29+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PostMapStructImpl implements PostMapStruct {

    @Override
    public void updateEntity(UpdatePostRequest dto, Post post) {
        if ( dto == null ) {
            return;
        }

        post.setTitle( dto.getTitle() );
        post.setContent( dto.getContent() );
    }

    @Override
    public PostResponse toResponse(PostVo post) {
        if ( post == null ) {
            return null;
        }

        PostResponse.PostResponseBuilder postResponse = PostResponse.builder();

        postResponse.postId( post.getPostId() );
        postResponse.title( post.getTitle() );
        postResponse.content( post.getContent() );
        if ( post.getStatus() != null ) {
            postResponse.status( post.getStatus().name() );
        }
        postResponse.createdAt( post.getCreatedAt() );
        postResponse.updatedAt( post.getUpdatedAt() );

        return postResponse.build();
    }
}
