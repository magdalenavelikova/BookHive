package com.bookhive.controller;


import com.bookhive.exception.UserNotFoundException;
import com.bookhive.exception.UserNotUniqueException;
import com.bookhive.model.dto.UserErrorDto;
import com.bookhive.model.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(UserNotUniqueException.class)
    public ResponseEntity<UserErrorDto> onUsernameNotUnique(UserNotUniqueException unue) {
        UserErrorDto userErrorDto = new UserErrorDto(unue.getUsername(), "Username is already exist!");

        return
                ResponseEntity.status(HttpStatus.CONFLICT).body(userErrorDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorDto> onUserNotFound(UserNotFoundException unfe) {
        UserErrorDto userErrorDto = new UserErrorDto(Long.toString(unfe.getId()), "User not found!");

        return
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(userErrorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status, WebRequest request) {
        AppException apiError = new AppException(
                request.getLocale().getLanguage(),
                HttpStatus.CONFLICT,
                ex.getLocalizedMessage(),
                getValidationErrors(ex.getBindingResult()));

        return handleExceptionInternal(ex, apiError, headers, status, request);

    }

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//        public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
//    }

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<?> handleMaxSizeException(
//            MaxUploadSizeExceededException exc,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
//    }
    private Map<String, String> getValidationErrors(BindingResult bindingResult) {
        Map<String, String> fieldErrors = new HashMap<>();

        bindingResult.getFieldErrors()
                .forEach(fieldError -> fieldErrors.put(fieldError.getField(),
                        fieldError.getDefaultMessage()));

        bindingResult.getGlobalErrors()
                .forEach(objectError -> fieldErrors.put(objectError.getObjectName(),
                        objectError.getDefaultMessage()));

        return fieldErrors;
    }


}
