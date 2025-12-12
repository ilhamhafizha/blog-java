package org.example.blog.request.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class LoginRequest {
    @Size(min =2 ,max = 100)
    @NotNull
    private String username;
    @Size(min =2 ,max = 100)
    @NotNull
    private String password;
}
