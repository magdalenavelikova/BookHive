package com.bookhive.service;

import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.model.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
@AllArgsConstructor
public class AuthService {
    private final JWTService jwtService;
    private RestTemplate restTemplate;

    private static final String LOGIN_URL = "http://user-service/users/login";


    public AuthResponse register(MultipartFile file, AuthRequest request) throws IOException {
        AuthResponse authResponse = new AuthResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        if (!file.isEmpty()) {
            body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
        }
        body.add("auth", request);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        try {
            UserVO registeredUser = restTemplate.postForObject("http://user-service/users/register", requestEntity, UserVO.class);
            String accessToken = jwtService.generate(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getRole(), "ACCESS");
            String refreshToken = jwtService.generate(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getRole(), "REFRESH");
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);
        } catch (HttpClientErrorException e) {
            authResponse.setAccessToken(e.getMessage());
        }
        return authResponse;
    }

    public Object registerConfirm(String token) {
        AuthRequest request = new AuthRequest();
        request.setToken(token);
        UserVO registeredUser = restTemplate.postForObject("http://user-service/users/registerConfirm", request, UserVO.class);
        String accessToken = jwtService.generate(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtService.generate(registeredUser.getId(), registeredUser.getUsername(), registeredUser.getRole(), "REFRESH");
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        return authResponse;
    }


    static class MultipartInputStreamFileResource extends InputStreamResource {

        private final String filename;

        MultipartInputStreamFileResource(InputStream inputStream, String filename) {
            super(inputStream);
            this.filename = filename;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public long contentLength() throws IOException {
            return -1; // we do not want to generally read the whole stream into memory ...
        }

    }

    public AuthResponse loginUser(AuthRequest request) {
        String accessToken;
        String refreshToken;
        AuthResponse authResponse = new AuthResponse();
        try {
            UserVO loginUser = restTemplate.postForObject(LOGIN_URL, request,
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

    public AuthResponse loginUserWithOAuth(AuthRequest request) {
        String accessToken;
        String refreshToken;
        AuthResponse authResponse = new AuthResponse();
        UserVO loginUser = restTemplate.postForObject("http://user-service/users/login-auth", request,
                UserVO.class);
        accessToken = jwtService.generate(loginUser.getId(), loginUser.getUsername(),
                loginUser.getRole(), "ACCESS");
        refreshToken = jwtService.generate(loginUser.getId(), loginUser.getUsername(),
                loginUser.getRole(), "REFRESH");
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        return authResponse;
    }

}
