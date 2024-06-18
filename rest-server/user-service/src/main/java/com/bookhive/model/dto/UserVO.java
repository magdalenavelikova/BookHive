package com.bookhive.model.dto;

import com.bookhive.model.entities.UserRoleEntity;
import com.bookhive.validation.annotation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String id;
    @NotEmpty(message = "User email should be provided.")
    @Email(message = "Please enter valid email.")
    @UniqueUserEmail(message = "There is already a registered user with this email address.")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
    private String password;
//    @NotEmpty
//    @Size(min = 5, max = 20, message = "Please enter between 5 and 20 characters.")
//    private String confirmPassword;
    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 30, message = "Please enter between 2 and 30 characters.")
    private String lastName;
    private String country;
    private String city;
    List<String> roles;
}
