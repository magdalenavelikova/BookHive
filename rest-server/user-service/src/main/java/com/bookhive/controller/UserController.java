package com.bookhive.controller;

import com.bookhive.model.dto.UserVO;
import com.bookhive.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserVO> register(@RequestBody @Valid UserVO userVO) {
        return ResponseEntity.ok(userService.registerNewUserAccount(userVO));
    }


    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello, from secured endpoint!");
    }

}