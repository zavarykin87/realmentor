package com.zavarykin.realmentor.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRegistrationEvent extends ApplicationEvent {

    private final String appUrl;
    private final String username;
    private final String email;

    public OnRegistrationEvent(String appUrl, String username, String email) {
        super(email);
        this.appUrl = appUrl;
        this.email = email;
        this.username = username;
    }

}
