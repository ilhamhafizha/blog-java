package org.example.blog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreatePostResponse {
    private String title;

    private String body;

    private String slug;

    private Long publishedAt;

    private Long commentCount;
}
