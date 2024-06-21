package com.bookhive.controller;

import com.bookhive.model.dto.AuthRequest;
import com.bookhive.model.dto.UserRegisterDto;
import com.bookhive.model.dto.UserVO;
import com.bookhive.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<UserVO> register(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("auth")  @Valid UserRegisterDto userRegisterDto

            ) throws IOException {
        return ResponseEntity.ok(userService.registerNewUserAccount(file,userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUserCredentials(@RequestBody AuthRequest userVO) {
        return ResponseEntity.ok(userService.getUserCredentials(userVO));
    }

    @PostMapping("/login-auth")
    public ResponseEntity<?> loginAuthCredentials(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.getAuthCredentials(request));
    }


    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello, from secured endpoint!");
    }

}
