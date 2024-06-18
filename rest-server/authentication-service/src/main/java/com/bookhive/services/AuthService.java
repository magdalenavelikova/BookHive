package com.bookhive.services;

import com.bookhive.models.AuthRequest;
import com.bookhive.models.AuthResponse;
import com.bookhive.models.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private RestTemplate restTemplate;


    public AuthResponse register(AuthRequest request) {
        //do validation if user exists in DB
        //request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO registeredUser = restTemplate.postForObject("http://user-service/users/register", request, UserVO.class);

        String accessToken = jwtService.generate(registeredUser.getId(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtService.generate(registeredUser.getId(), registeredUser.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
