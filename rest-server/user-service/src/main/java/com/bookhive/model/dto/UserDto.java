package com.bookhive.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private String role;
    private String created;
}
