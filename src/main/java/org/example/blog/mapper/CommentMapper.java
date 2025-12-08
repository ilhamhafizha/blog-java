package org.example.blog.mapper;

import org.example.blog.entity.Comment;
import org.example.blog.entity.Post;
import org.example.blog.request.CreateCommentRequest;
import org.example.blog.request.CreatePostRequest;
import org.example.blog.response.CreateCommentResponse;
import org.example.blog.response.CreatePostResponse;
import org.example.blog.response.GetPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapToCreateCommentResponse(CreateCommentRequest commentRequest);

    CreateCommentResponse mapToCreateCommentResponse(Comment comment);

}

