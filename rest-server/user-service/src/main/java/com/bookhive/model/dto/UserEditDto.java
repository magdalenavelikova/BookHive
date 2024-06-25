package com.bookhive.model.dto;

import com.bookhive.validation.annotation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEditDto {
    @NotEmpty(message = "Username should be provided.")
    private String username;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "Please enter valid email.")
     private String email;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String lastName;

    private String avatar;

    @NotEmpty
    private String role;
}
