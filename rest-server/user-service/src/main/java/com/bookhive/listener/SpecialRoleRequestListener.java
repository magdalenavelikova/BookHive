package com.bookhive.listener;


import com.bookhive.event.OnSpecialRoleRequestCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SpecialRoleRequestListener implements
        ApplicationListener<OnSpecialRoleRequestCompleteEvent> {
    //TODO implement KAFKA to export massage to notification service
//    private final MembershipRequestMailService mailService;
//


    @Override
    public void onApplicationEvent(OnSpecialRoleRequestCompleteEvent event) {
//        try {
//            mailService.sendMembershipRequestEmail(event.getUsername(), event.getLocale());
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
    }
}
