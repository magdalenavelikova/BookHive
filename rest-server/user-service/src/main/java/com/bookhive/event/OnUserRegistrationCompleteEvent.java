package com.bookhive.event;


import com.bookhive.model.dto.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnUserRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;

    private UserVO user;

    public OnUserRegistrationCompleteEvent(Object source,
                                           UserVO user, String appUrl) {
        super(source);
        this.user = user;
        this.appUrl = appUrl;
    }


}
