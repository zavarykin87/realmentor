package com.zavarykin.realmentor.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRestoreLoginEvent extends ApplicationEvent {

    private final String email;

    public OnRestoreLoginEvent(String email) {
        super(email);
        this.email = email;
    }
}
