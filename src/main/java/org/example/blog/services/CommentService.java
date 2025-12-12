package org.example.blog.services;

import jakarta.transaction.Transactional;
import org.example.blog.entity.Comment;
import org.example.blog.entity.Post;
import org.example.blog.exeption.ApiException;
import org.example.blog.mapper.CommentMapper;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.PostRepository;
import org.example.blog.request.comment.CreateCommentRequest;
import org.example.blog.request.comment.GetCommentByIdRequest;
import org.example.blog.request.comment.GetCommentsRequest;
import org.example.blog.response.comment.CreateCommentResponse;
import org.example.blog.response.comment.GetCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public List<GetCommentResponse> getComments(GetCommentsRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPostSlug(), false)
                .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(request.getPageNo(), request.getLimit());
        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();
        List<GetCommentResponse> responses = new ArrayList<>();
        comments.forEach(comment -> responses.add(CommentMapper.INSTANCE.mapToGetCommentResponse(comment)));
        return responses;
    }

    public GetCommentResponse getComment(GetCommentByIdRequest request) {
        Comment comment = commentRepository.findById(request.getId())
                .orElseThrow(() -> new ApiException("comment not found", HttpStatus.NOT_FOUND));
        return CommentMapper.INSTANCE.mapToGetCommentResponse(comment);
    }


    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPost().getSlug(), false).
                orElseThrow(()
                        -> new ApiException("post not found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.mapToCreateCommentResponse(request);

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.getPost().setId(post.getId());
        commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(comment);
    }
}

