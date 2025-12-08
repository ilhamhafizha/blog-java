package org.example.blog.mapper;

import org.example.blog.entity.Comment;
import org.example.blog.request.comment.CreateCommentRequest;
import org.example.blog.response.comment.CreateCommentResponse;
import org.example.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapToCreateCommentResponse(CreateCommentRequest commentRequest);

    CreateCommentResponse mapToCreateCommentResponse(Comment comment);

    GetCommentResponse mapToGetCommentResponse(Comment comment);
}

