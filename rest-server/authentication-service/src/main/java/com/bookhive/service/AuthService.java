package com.bookhive.service;

import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.model.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private RestTemplate restTemplate;


    public AuthResponse register(AuthRequest request) {

        //TODO validation if user exists in DB

        UserVO registeredUser = restTemplate.postForObject("http://user-service/users/register", request, UserVO.class);

        String accessToken = jwtService.generate(registeredUser.getId(), registeredUser.getRoles().get(0), "ACCESS");
        String refreshToken = jwtService.generate(registeredUser.getId(), registeredUser.getRoles().get(0), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
