package org.example.blog.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.blog.entity.Post;
import org.example.blog.exeption.ApiException;
import org.example.blog.mapper.PostMapper;
import org.example.blog.repository.PostRepository;
import org.example.blog.request.CreatePostRequest;
import org.example.blog.request.GetPostBySlugRequest;
import org.example.blog.response.CreatePostResponse;
import org.example.blog.response.GetPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class PostService {

    @Autowired
    PostRepository postRepository;

    public GetPostResponse getPostBySlug(GetPostBySlugRequest response) {
        Post post = postRepository
                .findFirstBySlugAndIsDeleted(response.getSlug(), false)
                .orElseThrow(() -> new ApiException("Not Found", HttpStatus.NOT_FOUND));

        return PostMapper.INSTANCE.mapToGetPostResponse(post);
    }

    public CreatePostResponse addPost(CreatePostRequest request) {
        Post post = PostMapper.INSTANCE.mapToCreatePostResponse(request);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);
        return PostMapper.INSTANCE.mapToCreatePostResponse(post);
    }

    public Post updatePost(String slug,Post sendPostByUser) {
        Post savePost = postRepository.findFirstBySlugAndIsDeleted(slug,false).orElse(null);
        if (savePost == null){
            return  null;
        }sendPostByUser.setId(savePost.getId());
        return postRepository.save(sendPostByUser);
    }

    public Boolean deletePost(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            return  false;
        }
        post.setDeleted(true);
        postRepository.save(post);
        return true;
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
