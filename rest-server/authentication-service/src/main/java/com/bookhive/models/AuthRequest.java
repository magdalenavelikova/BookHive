package com.bookhive.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// TODO: 17.6.2024 г. All fields for register a user
public class AuthRequest {

    private String email;
    private String password;
    private String name;
}
