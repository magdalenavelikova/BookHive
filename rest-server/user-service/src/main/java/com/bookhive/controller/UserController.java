package com.bookhive.controller;

import com.bookhive.model.dto.UserLoginDTO;
import com.bookhive.model.dto.UserRegisterDto;
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
    public ResponseEntity<UserVO> register(@RequestBody  @Valid UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(userService.registerNewUserAccount(userRegisterDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUserCredentials(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(userService.getUserCredentials(userLoginDTO));
    }


    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello, from secured endpoint!");
    }

}
