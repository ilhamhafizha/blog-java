package org.example.blog.controller;

import org.example.blog.entity.Post;
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
    public Post getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping("/")
    public Post addPost(@RequestBody Post post) {
        post.setCreatedAt(Instant.now().getEpochSecond());
       return postService.addPost(post);
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
