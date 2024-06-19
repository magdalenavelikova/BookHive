package com.bookhive.exception;

public class UserNotFoundExceptionInLogin extends RuntimeException{

    public UserNotFoundExceptionInLogin(String message){
        super(message);
    }
}
