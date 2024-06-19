package com.bookhive.controller;

import com.bookhive.model.AuthRequest;
import com.bookhive.model.AuthResponse;
import com.bookhive.service.AuthService;
import lombok.AllArgsConstructor;
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
       return ResponseEntity.ok(authService.register(file,request));

    }
}
