package com.bookhive.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
