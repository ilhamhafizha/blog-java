package org.example.blog.mapper;

import org.example.blog.entity.Post;
import org.example.blog.request.CreatePostRequest;
import org.example.blog.response.CreatePostResponse;
import org.example.blog.response.GetPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post mapToCreatePostResponse(CreatePostRequest postRequest);

    @Mapping(source = "slug", target = "path")
    CreatePostResponse mapToCreatePostResponse(Post post);

    GetPostResponse mapToGetPostResponse(Post post);

    //List<GetPostResponse> mapToGetPostResponse(List<Post> posts);
}

