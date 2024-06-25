package com.bookhive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private String accessToken;
    private String refreshToken;
}
