package com.bookhive.listener;


import com.bookhive.event.OnForgottenPasswordCompleteEvent;
import com.bookhive.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserForgottenPasswordListener implements
        ApplicationListener<OnForgottenPasswordCompleteEvent> {
    private final UserService userService;
   // private final UserForgottenPasswordMailServiceImpl userForgottenPasswordMailService;



    @Override
    public void onApplicationEvent(@NotNull OnForgottenPasswordCompleteEvent event) {
        try {
            this.changeForgottenPassword(event);
        } catch ( UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeForgottenPassword(OnForgottenPasswordCompleteEvent event) throws  UnsupportedEncodingException {
//        UserDto user = userService.getUserByUserEmail(event.getRequest());
//        UserEntity userEntity = userService.getUserByUserEmail(user.getEmail());
//        VerificationToken verificationToken = userService.getVerificationTokenByUser(userEntity);
//        String token;
//        if (verificationToken == null) {
//            token = UUID.randomUUID().toString();
//            userService.createVerificationToken(user, token);
//        } else {
//            token = verificationToken.getToken();
//        }
//        String confirmationUrl
//                = event.getAppUrl() + "/new-password?token=" + token;
//        userForgottenPasswordMailService.sendForgottenPasswordEmail(user.getFullName(),
//                user.getEmail(),
//                event.getLocale(),
//                confirmationUrl);
  }
}
