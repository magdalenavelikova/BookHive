package com.bookhive.listener;


import com.bookhive.event.OnUserRegistrationCompleteEvent;
import com.bookhive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserRegistrationListener implements
        ApplicationListener<OnUserRegistrationCompleteEvent> {


    private final UserService userService;
 //   private final UserRegistrationMailServiceImpl userRegistrationMailService;




    @Override
    public void onApplicationEvent(OnUserRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch ( UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmRegistration(OnUserRegistrationCompleteEvent event) throws  UnsupportedEncodingException {
//        UserDto user = event.getUser();
//
//        String token = UUID.randomUUID().toString();
//
//        userService.createVerificationToken(user, token);
//        String confirmationUrl
//                = event.getAppUrl() + "Confirm?token=" + token;
//        userRegistrationMailService.sendVerificationEmail(user.getFullName(),
//                user.getEmail(),
//                event.getLocale(),
//                confirmationUrl);
    }
}