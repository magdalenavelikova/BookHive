package com.bookhive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String id;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
}
