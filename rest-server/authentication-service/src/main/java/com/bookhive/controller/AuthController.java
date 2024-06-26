package com.bookhive.controller;

import com.bookhive.errors.CustomError;
import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<AuthResponse> register(
            @RequestPart("auth") AuthRequest request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(authService.register(file, request));

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

    @PostMapping("/oauth")
    public ResponseEntity<?> oauthLogin(@RequestBody AuthRequest request) {
        AuthResponse authResponse = this.authService.loginUserWithOAuth(request);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/registerConfirm")
    public ResponseEntity<?> confirmRegistration
            (@RequestParam("token") String token) {

        return ResponseEntity.ok(authService.registerConfirm(token));
    }
}
