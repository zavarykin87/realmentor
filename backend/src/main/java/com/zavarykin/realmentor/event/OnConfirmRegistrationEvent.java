package com.zavarykin.realmentor.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnConfirmRegistrationEvent extends ApplicationEvent {

    private final String token;

    public OnConfirmRegistrationEvent(String token) {
        super(token);
        this.token = token;
    }
}
