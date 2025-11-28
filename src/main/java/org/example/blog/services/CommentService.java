package org.example.blog.services;

import org.example.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    public Iterable<Comment> getComments(String postSlug, Integer pageNo, Integer limit) {
        List<Comment> commentList = new ArrayList<>();
        return commentList;
    }

    public Comment getComments( Integer id) {
        return new Comment();
    }

    public Comment createComments( Comment comment) {
        return comment;
    }
}

