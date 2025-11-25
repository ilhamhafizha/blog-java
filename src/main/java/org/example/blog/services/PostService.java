package org.example.blog.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.blog.entity.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class PostService {
    Post pos1 = new Post(1, "title_1", "slug_1");
    Post pos2 = new Post(2, "title_2", "slug_2");
    @Getter
    List<Post> posts =new ArrayList<Post>(Arrays.asList(pos1, pos2));

    public Post getPostBySlug(String slug) {
        return posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst().orElse(null);
    }

    public Post addPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post updatePost(String slug,Post sendPostByUser) {
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

    public Boolean deletePost(Integer id) {
        Post savePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (savePost == null){
            return  false;
        }
        posts.remove(savePost);
        return  true;
    }

    public Post publishPost(Integer id) {
        Post savePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (savePost == null){
            return  null;
        }
        savePost.setPublished(true);
        return  savePost;
    }
}
