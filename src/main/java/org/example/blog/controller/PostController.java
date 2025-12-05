package org.example.blog.controller;

import jakarta.validation.Valid;
import org.example.blog.entity.Post;
import org.example.blog.request.CreatePostRequest;
import org.example.blog.request.GetPostBySlugRequest;
import org.example.blog.response.CreatePostResponse;
import org.example.blog.response.GetPostResponse;
import org.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPostBySlug(@PathVariable String slug) {

        GetPostBySlugRequest request = GetPostBySlugRequest.builder()
                .slug(slug)
                .build();

        return postService.getPostBySlug(request);
    }

    @PostMapping
    public CreatePostResponse addPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
       return postService.addPost(createPostRequest);
    }

    @PutMapping("/{slug}")
    public Post updatePost(@PathVariable String slug,@RequestBody Post sendPostByUser) {
        return postService.updatePost(slug,sendPostByUser);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePost(@PathVariable Integer id) {
        return postService.deletePost(id);
    }

    @PostMapping("/{id}/publish")
    public Post publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }
}
