package com.bookhive.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;


@Service
public class RouterValidator {
    public static final List<String> openEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/oauth",
            "/auth/registerConfirm",
            "/users/forgotten-password",
            "/users/forgotten-password/new-password"


    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}

