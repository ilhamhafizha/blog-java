package org.example.blog.mapper;

import org.example.blog.entity.Post;
import org.example.blog.request.CreatePostRequest;
import org.example.blog.response.CreatePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post map (CreatePostRequest postRequest);

    @Mapping(source = "slug", target = "path")
    CreatePostResponse map (Post post);
}
