package com.bookhive.service;

import com.bookhive.dto.AuthResponse;
import com.bookhive.dto.OAuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@AllArgsConstructor
public class OAuthService {

    private RestTemplate restTemplateOauth;


    public AuthResponse loginUserWithOAuth(Map<String, Object> attributes) {
        OAuthDTO oAuthDTO = new OAuthDTO();
        String email = attributes.getOrDefault("email", "").toString();
        oAuthDTO.setUsername(email.substring(0, email.indexOf("@")));
        oAuthDTO.setEmail(email);
        oAuthDTO.setFirstName(attributes.getOrDefault("given_name", "").toString());
        oAuthDTO.setLastName(attributes.getOrDefault("family_name", "").toString());
        oAuthDTO.setAvatar(attributes.getOrDefault("picture", "").toString());
        HttpEntity<OAuthDTO> request = new HttpEntity<>(oAuthDTO);
        AuthResponse authResponse = restTemplateOauth.postForObject("http://auth-service//auth/oauth", request, AuthResponse.class);
        System.out.println(authResponse);
        return authResponse;

    }
}
