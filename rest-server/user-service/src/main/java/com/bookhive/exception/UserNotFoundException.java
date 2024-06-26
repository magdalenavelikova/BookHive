package com.bookhive.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User was not found.")
@Getter
public class UserNotFoundException extends RuntimeException {
    private Long id;
    private String username;

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found!");
        this.id = id;
    }

    public UserNotFoundException(String username) {
        super("User with username " + username + " not found!");
        this.username = username;
    }


}
