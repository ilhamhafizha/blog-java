package org.example.blog.controller;

import org.example.blog.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    Post pos1 = new Post(1, "title_1", "slug_1");
    Post pos2 = new Post(2, "title_2", "slug_2");
    List<Post> posts =new ArrayList<Post>(Arrays.asList(pos1, pos2));

    @GetMapping("/")
    public List<Post> getPosts() {
        return posts;
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
    }

    @PostMapping("/")
    public Post addPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/{slug}")
    public Post updatePost(@PathVariable String slug,@RequestBody Post sendPostByUser) {
      Post savePost = posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
        if (savePost == null){
            return  null;
        }
        savePost.setTitle(sendPostByUser.getTitle());
        savePost.setSlug(sendPostByUser.getSlug());
        posts.add(savePost);
        posts.remove(savePost);
      return  savePost;
    }

    @DeleteMapping("/{id}")
    public Boolean deletePost(@PathVariable Integer id) {
        Post savePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (savePost == null){
            return  false;
        }

        posts.remove(savePost);
        return  true;
    }

    @PostMapping("/{id}/publish")
    public Post publishPost(@PathVariable Integer id) {
        Post savePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (savePost == null){
            return  null;
        }
        savePost.setPublished(true);
        return  savePost;
    }
}
