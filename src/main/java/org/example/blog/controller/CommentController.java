package org.example.blog.controller;

import jakarta.validation.Valid;
import org.example.blog.entity.Comment;
import org.example.blog.request.CreateCommentRequest;
import org.example.blog.response.CreateCommentResponse;
import org.example.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public Iterable<Comment> getComments(@RequestParam(required = false) String postSlug,
                                         @RequestParam(required = false) Integer pageNo,
                                         @RequestParam(required = false) Integer limit) {
      return commentService.getComments(postSlug, pageNo, limit);
    }

    @GetMapping("/{id}")
    public Comment getComments(@PathVariable Integer id) {
        return commentService.getComments(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment) {
      //  comment.setCreatedAt(Instant.now().getEpochSecond());
        return commentService.createComment(comment);
    }
}
