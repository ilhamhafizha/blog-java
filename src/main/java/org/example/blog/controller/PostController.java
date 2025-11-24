package org.example.blog.controller;

import org.example.blog.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    Post pos1 = new Post();
    List<Post> posts = List.of(pos1);

    @GetMapping("/")
    public List<Post> getPosts() {
        return List.of(new Post());
    }
}
