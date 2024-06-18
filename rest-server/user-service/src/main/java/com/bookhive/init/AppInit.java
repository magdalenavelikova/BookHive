package com.bookhive.init;

import com.bookhive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppInit implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.initRole();
    }
}
