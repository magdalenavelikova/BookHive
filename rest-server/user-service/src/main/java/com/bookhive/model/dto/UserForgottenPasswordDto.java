package com.bookhive.model.dto;

import com.bookhive.validation.annotation.FieldMatch;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@FieldMatch(first = "password",
        second = "confirmPassword",
        message = "Passwords do not match!")
public class UserForgottenPasswordDto {
    @NotEmpty
    private String verificationToken;

    @NotEmpty
    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
    private String password;

    @NotEmpty
    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
    private String confirmPassword;


}
