package com.bookhive.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppException {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String locale;
    private HttpStatus status;

    private String message;
    private Map<String, String> fieldErrors;

    public AppException(String locale, HttpStatus status, String message, Map<String, String> fieldErrors) {

        this.locale = locale;
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
