package com.zavarykin.realmentor.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRestorePasswordEvent extends ApplicationEvent {

    private final String email;
    private final String appUrl;

    public OnRestorePasswordEvent(String email, String appUrl) {
        super(email);
        this.email = email;
        this.appUrl = appUrl;
    }
}
