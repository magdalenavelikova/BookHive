package com.bookhive.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
// TODO: 17.6.2024 г. All fields for register a user
public class AuthRequest {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
