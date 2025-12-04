package org.example.blog.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CreatePostRequest {
    @Size(min = 2, message = "minimal 2")
    @NotNull
    private String title;
    @Size(min = 2, message = "minimal 2")
    private String body;
    @Size(min = 2, message = "minimal 2")
    private String slug;
}
