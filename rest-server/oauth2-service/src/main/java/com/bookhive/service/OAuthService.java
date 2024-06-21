package com.bookhive.service;

import com.bookhive.dto.OAuthDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class OAuthService {

    private RestTemplate restTemplate;

    private static final String AUTH_SERVICE_URL = "http://auth-service/auth/oauth";


    public void sendEmail(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        OAuthDTO oAuthDTO = new OAuthDTO();
        oAuthDTO.setEmail(email);
        HttpEntity<OAuthDTO> request = new HttpEntity<>(oAuthDTO, headers);
        OAuthDTO authUser = restTemplate.postForObject("http://localhost:9001/auth/oauth", request, OAuthDTO.class);

    }
}
