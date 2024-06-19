package com.bookhive.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Email/Username is not unique.")
@Getter
public class UserNotUniqueException extends RuntimeException {

    private final String username;

    public UserNotUniqueException(String username) {
        super("There is already a registered user with this email " + username + "!");
        this.username = username;
    }



}
