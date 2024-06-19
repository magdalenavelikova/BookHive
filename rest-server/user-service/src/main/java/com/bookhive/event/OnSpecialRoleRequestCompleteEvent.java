package com.bookhive.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;
@Getter
@Setter
public class OnSpecialRoleRequestCompleteEvent extends ApplicationEvent {

    private String username;
    private Locale locale;

    public OnSpecialRoleRequestCompleteEvent(Object source, String username, Locale locale) {
        super(source);

        this.username = username;
        this.locale = locale;
    }



}
