package org.example.blog.services;

import jakarta.transaction.Transactional;
import org.example.blog.entity.Comment;
import org.example.blog.entity.Post;
import org.example.blog.mapper.CommentMapper;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.PostRepository;
import org.example.blog.request.CreateCommentRequest;
import org.example.blog.response.CreateCommentResponse;
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


    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPost().getSlug(), false).orElse(null);
        if (post == null) {
            return null;
        }

        Comment comment = CommentMapper.INSTANCE.mapToCreateCommentResponse(request);

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.getPost().setId(post.getId());
        commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(comment);
    }
}

