package com.bookhive.exception;

public class WrongPasswordExceptionInLogin extends RuntimeException{

    public WrongPasswordExceptionInLogin(String message) {
        super(message);
    }
}
