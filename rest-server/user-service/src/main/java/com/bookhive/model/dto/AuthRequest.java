package com.bookhive.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @NotEmpty(message = "Username should be provided.")
    private String username;
    @NotEmpty(message = "Password should be provided.")
    private String password;


}
