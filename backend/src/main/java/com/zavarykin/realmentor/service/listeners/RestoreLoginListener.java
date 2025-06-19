package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.event.OnRestoreLoginEvent;
import com.zavarykin.realmentor.exception.ObjectNotFoundException;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RestoreLoginListener implements ApplicationListener<OnRestoreLoginEvent> {

    private static final String SUBJECT = "Напоминание логина";
    private static final String MESSAGE = "Ваш логин: %s";

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRestoreLoginEvent event) {
        val email = event.getEmail();
        val user = userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(email));
        emailService.sendSimpleMessage(email, SUBJECT, String.format(MESSAGE, user.getUsername()));
    }
}
