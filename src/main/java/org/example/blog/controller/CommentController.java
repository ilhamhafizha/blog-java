package org.example.blog.controller;

import jakarta.validation.Valid;
import org.example.blog.entity.Comment;
import org.example.blog.exeption.ApiException;
import org.example.blog.mapper.CommentMapper;
import org.example.blog.request.comment.CreateCommentRequest;
import org.example.blog.request.comment.GetCommentByIdRequest;
import org.example.blog.request.comment.GetCommentsRequest;
import org.example.blog.response.comment.CreateCommentResponse;
import org.example.blog.response.comment.GetCommentResponse;
import org.example.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam String postSlug,
                                                @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetCommentsRequest request = GetCommentsRequest.builder()
                .postSlug(postSlug)
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return commentService.getComments(request);
    }

    @GetMapping("/{id}")
    public GetCommentResponse getComment(@PathVariable Integer id) {
        GetCommentByIdRequest request = GetCommentByIdRequest.builder().id(id).build();
        return commentService.getComment(request);
    }


    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment) {
        return commentService.createComment(comment);
    }
}
