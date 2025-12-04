package org.example.blog.mapper;

import org.example.blog.entity.Post;
import org.example.blog.request.CreatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post map (CreatePostRequest createPostRequest);
}
