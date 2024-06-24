package com.bookhive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "User or password incorrect.")
public class UserLoginException extends RuntimeException{

    public UserLoginException(String message){
        super(message);
    }
}
