package com.bookhive.event;


import com.bookhive.model.dto.AuthRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnForgottenPasswordCompleteEvent extends ApplicationEvent {
    private String appUrl;

    private AuthRequest request;

    public OnForgottenPasswordCompleteEvent(Object source, String appUrl,  AuthRequest request) {
        super(source);
        this.appUrl = appUrl;
        this.request = request;
    }


}
