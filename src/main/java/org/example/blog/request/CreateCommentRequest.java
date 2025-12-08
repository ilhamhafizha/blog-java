package org.example.blog.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blog.entity.Post;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    @Size(min = 2, max = 100)
    @NotNull
    private String name;
    @Size(min = 2, max = 100)
    @Email
    @NotNull
    private String email;

    @NotNull
    private Post post;
    @NotNull
    @Size(min = 2)
    private String body;
}
