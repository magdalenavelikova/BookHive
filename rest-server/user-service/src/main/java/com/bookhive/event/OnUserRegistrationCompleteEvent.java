package com.bookhive.event;


import com.bookhive.model.dto.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
@Getter
@Setter
public class OnUserRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UserVO user;

    public OnUserRegistrationCompleteEvent(Object source,
                                           UserVO user, Locale locale, String appUrl) {
        super(source);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }




}
