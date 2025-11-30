package org.example.blog.services;

import org.example.blog.entity.Comment;
import org.example.blog.entity.Post;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public Iterable<Comment> getComments(String postSlug, Integer pageNo, Integer limit) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(postSlug, false).orElse(null);
        if (post == null) {
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(),pageRequest).getContent();
    }

    public Comment getComments( Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComments( Comment comment) {
        comment.setCreatedAt(Instant.now().getEpochSecond());
        return commentRepository.save(comment);
    }
}

