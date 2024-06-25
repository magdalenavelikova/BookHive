package com.bookhive.controller;

import com.bookhive.model.dto.*;
import com.bookhive.model.enums.Role;
import com.bookhive.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<UserVO> register(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("auth") @Valid UserRegisterDto userRegisterDto

    ) throws IOException {
        return ResponseEntity.ok(userService.registerNewUserAccount(file, userRegisterDto, false));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUserCredentials(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(userService.getUserCredentials(request));
    }

    @PostMapping("/login-auth")
    public ResponseEntity<?> loginAuthCredentials(@RequestBody UserRegisterDto request) throws IOException {
        return ResponseEntity.ok(userService.getAuthCredentials(request));
    }

    //    @GetMapping("/registerConfirm")
//    public ResponseEntity<?> confirmRegistration
//            (@RequestParam("token") String token) {
//
//        VerificationToken verificationToken = userService.getVerificationToken(token);
//        ResponseEntity<String> UNAUTHORIZED = getStringResponseEntity(verificationToken);
//        if (UNAUTHORIZED != null) return UNAUTHORIZED;
//
//        UserDto user = userService.getUserByVerificationToken(verificationToken);
//        userService.saveRegisteredUser(user);
//
//        return ResponseEntity.ok()
//                .header(
//                        HttpHeaders.AUTHORIZATION,
//                        jwtService.generateToken(userDetails)
//                )
//                .body(user);
//    }
//
  @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader HttpHeaders headers) {
      String role = headers.getFirst("X-User-Role");
      if ( Objects.requireNonNull(role).equals(Role.ADMIN.toString())
              || role.equals(Role.MODERATOR.toString())) {
          return ResponseEntity.ok()
                  .body(userService.getAllUsers());
      } else
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        String role = headers.getFirst("X-User-Role");
        if ( Objects.requireNonNull(role).equals(Role.ADMIN.toString())
                || role.equals(Role.MODERATOR.toString())) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@RequestHeader HttpHeaders headers, @RequestBody @Valid UserEditDto editUserDto, @PathVariable Long id) {
        String role = headers.getFirst("X-User-Role");
        String idHeader = headers.getFirst("X-User-Id");
        if (id.equals(Long.valueOf(Objects.requireNonNull(idHeader)))
                || Objects.requireNonNull(role).equals(Role.ADMIN.toString())
                || role.equals(Role.MODERATOR.toString())) {

            return ResponseEntity.ok()
                    .body(userService.editUserCredential(editUserDto, id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEditDto> getUser(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        //   String username = headers.getFirst("X-User-Username");
        String role = headers.getFirst("X-User-Role");
        String idHeader = headers.getFirst("X-User-Id");
        if (id.equals(Long.valueOf(Objects.requireNonNull(idHeader)))
                || Objects.requireNonNull(role).equals(Role.ADMIN.toString())
                || role.equals(Role.MODERATOR.toString())) {
            return ResponseEntity.ok()
                    .body(userService.getUser(id));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordDto userChangePasswordDto,
                                            @RequestHeader HttpHeaders headers) {
        String username = headers.getFirst("X-User-Username");
        if (userService.changePassword(userChangePasswordDto, username)) {
            String messageValue = "Successfully changed password";
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("{\"message\": \"" + messageValue + "\"}");
        }
        String messageValue = "Old password does not match!";
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("{\"message\": \"" + messageValue + "\" }");
    }

//    @PostMapping("/forgotten-password")
//    public ResponseEntity<?> forgottenPassword(@RequestBody AuthRequest authRequest, ServletWebRequest request) {
//        if (isValid(authRequest) != null) {
//            userService.forgottenPassword(authRequest, request);
//            String messageValue = "Email was send";
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"" + messageValue + "\" }");
//        }
//        String messageValue = "Invalid email address";
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"" + messageValue + "\" }");
//
//    }
//    @PatchMapping("/forgotten-password/new-password")
//    public ResponseEntity<?> forgottenPasswordNewPassword(@RequestBody @Valid UserForgottenPasswordDto forgottenPasswordNewPasswordDto) {
//        VerificationToken verificationToken = userService.getVerificationToken(forgottenPasswordNewPasswordDto.getVerificationToken());
//        ResponseEntity<String> UNAUTHORIZED =
//                getStringResponseEntity(verificationToken);
//        if (UNAUTHORIZED != null) return UNAUTHORIZED;
//        userService.setNewPassword(forgottenPasswordNewPasswordDto);
//        String messageValue = "Successfully changed password";
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ \"message\": \"" + messageValue + "\" }");
//    }
//

    @GetMapping("/secured")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Hello, from secured endpoint!");
    }

}
