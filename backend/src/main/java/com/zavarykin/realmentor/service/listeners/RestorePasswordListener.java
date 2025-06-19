package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.event.OnRestorePasswordEvent;
import com.zavarykin.realmentor.exception.ObjectNotFoundException;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.EmailService;
import com.zavarykin.realmentor.service.UserTokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestorePasswordListener implements ApplicationListener<OnRestorePasswordEvent> {

    private final UserRepository userRepository;
    private final UserTokenService tokenService;
    private final EmailService emailService;

    private static final String SUBJECT = "Восстановление пароля";

    @Override
    public void onApplicationEvent(OnRestorePasswordEvent event) {
        val email = event.getEmail();
        val url = event.getAppUrl();
        val user = userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(email));
        val token = tokenService.createToken(user.getUsername()).getToken();
        val message = url + "/resetPassword?token=" + token;
        emailService.sendSimpleMessage(email, SUBJECT, message);
    }
}
