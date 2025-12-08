package org.example.blog.controller;

import jakarta.validation.Valid;
import org.example.blog.entity.Post;
import org.example.blog.request.post.CreatePostRequest;
import org.example.blog.request.post.GetPostBySlugRequest;
import org.example.blog.response.post.CreatePostResponse;
import org.example.blog.response.post.DeletePostByIdResponse;
import org.example.blog.response.post.GetPostResponse;
import org.example.blog.response.post.PublishPostResponse;
import org.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public DeletePostByIdResponse deletePost(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public PublishPostResponse publishPost(@PathVariable Integer id) {
        return postService.publishPost(id);
    }
}
