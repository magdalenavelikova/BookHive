package com.bookhive.model.dto;

import com.bookhive.validation.annotation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegisterDto {
    @NotEmpty(message = "Username should be provided.")
    @UniqueUserEmail(message = "There is already a registered user with this username.")
    private String username;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "Please enter valid email.")
    @UniqueUserEmail(message = "There is already a registered user with this email address.")
    private String email;

    @NotEmpty
    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
    private String password;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String lastName;

    private String avatar;

}
