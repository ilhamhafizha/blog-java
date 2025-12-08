package org.example.blog.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.blog.entity.Post;
import org.example.blog.exeption.ApiException;
import org.example.blog.mapper.PostMapper;
import org.example.blog.repository.PostRepository;
import org.example.blog.request.post.CreatePostRequest;
import org.example.blog.request.post.GetPostBySlugRequest;
import org.example.blog.response.post.CreatePostResponse;
import org.example.blog.response.post.DeletePostByIdResponse;
import org.example.blog.response.post.GetPostResponse;
import org.example.blog.response.post.PublishPostResponse;
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

    public DeletePostByIdResponse deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setDeleted(true);
        postRepository.save(post);
        return DeletePostByIdResponse.builder().id(id).build();
    }

    public PublishPostResponse publishPost(Integer id) {
        Post post = postRepository.findByIdAndIsDeleted(id, false).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        postRepository.save(post);
        return PublishPostResponse.builder().publishedAt(post.getPublishedAt()).build();
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}
