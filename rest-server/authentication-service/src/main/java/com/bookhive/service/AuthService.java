package com.bookhive.service;

import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.model.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private RestTemplate restTemplate;

    private static final String LOGIN_URL = "http://user-service/users/login";


    public AuthResponse register(AuthRequest request) {
       //TODO validation if user exists in DB
        UserVO registeredUser = restTemplate.postForObject(LOGIN_URL, request, UserVO.class);
        String accessToken = jwtService.generate(registeredUser.getId(),registeredUser.getUsername(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtService.generate(registeredUser.getId(), registeredUser.getUsername(),registeredUser.getRole(), "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse loginUser(AuthRequest request) throws Exception {
        String accessToken;
        String refreshToken;
        AuthResponse authResponse = new AuthResponse();
        try {
            UserVO loginUser = restTemplate.postForObject("http://user-service/users/login", request,
                    UserVO.class);
            accessToken = jwtService.generate(loginUser.getId(), loginUser.getUsername(),
                    loginUser.getRole(), "ACCESS");
            refreshToken = jwtService.generate(loginUser.getId(), loginUser.getUsername(),
                    loginUser.getRole(), "REFRESH");
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);
        } catch (HttpClientErrorException e) {
                authResponse.setAccessToken(e.getMessage());
        }
        return authResponse;
    }
}
