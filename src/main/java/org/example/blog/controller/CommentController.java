package org.example.blog.controller;

import org.example.blog.entity.Comment;
import org.example.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;


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
    public Comment createComments(@RequestBody Comment comment) {
      //  comment.setCreatedAt(Instant.now().getEpochSecond());
        return commentService.createComments(comment);
    }
}
