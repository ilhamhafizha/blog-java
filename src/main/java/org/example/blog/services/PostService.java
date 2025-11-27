package org.example.blog.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.blog.entity.Post;
import org.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post getPostBySlug(String slug) {
        return postRepository.findFirstBySlugAndIsDeleted(slug,false).orElse(null);
    }

    public Post addPost(Post post) {
        post.setCreatedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }

    public Post updatePost(String slug,Post sendPostByUser) {
        Post savePost = postRepository.findFirstBySlugAndIsDeleted(slug,false).orElse(null);
        if (savePost == null){
            return  null;
        }
        sendPostByUser.setId(savePost.getId());
        return postRepository.save(sendPostByUser);
    }

    public Boolean deletePost(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return  false;
        }
        post.setDeleted(true);
        postRepository.save(post);
        return  true;
    }

    public Post publishPost(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return  null;
        }
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}
