package com.bookhive.controller;

import com.bookhive.errors.CustomError;
import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) throws Exception {
        AuthResponse authResponse = this.authService.loginUser(request);
        if (authResponse.getRefreshToken() != null) {
            return ResponseEntity.ok(authResponse);
        } else {
            CustomError customError = new CustomError();
            customError.setError(authResponse.getAccessToken());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
        }
    }
}
