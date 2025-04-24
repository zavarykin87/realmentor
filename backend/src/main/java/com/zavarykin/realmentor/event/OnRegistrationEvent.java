package com.zavarykin.realmentor.event;

import org.springframework.context.ApplicationEvent;

public class OnRegistrationEvent extends ApplicationEvent {

    private String appUrl;
    private String username;
    private String email;

    public OnRegistrationEvent(String appUrl, String username, String email) {
        super(email);
        this.appUrl = appUrl;
        this.email = email;
        this.username = username;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
