package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.event.OnConfirmRegistrationEvent;
import com.zavarykin.realmentor.service.UserService;
import com.zavarykin.realmentor.service.UserTokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConfirmRegistrationListener implements ApplicationListener<OnConfirmRegistrationEvent> {

    private final UserTokenService tokenService;
    private final UserService userService;

    @Override
    public void onApplicationEvent(OnConfirmRegistrationEvent event) {
        val tokenEntity = tokenService.getByToken(event.getToken());
        val username = tokenEntity.getUserEntity().getUsername();
        val user = userService.getByUsername(username);
        user.setEnabled(true);
        user.setUserTokenEntity(null);
        userService.saveUser(user);
    }
}
