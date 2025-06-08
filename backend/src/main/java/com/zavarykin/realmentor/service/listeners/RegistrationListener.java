package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.EmailService;
import com.zavarykin.realmentor.service.RegistrationTokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {

    private final EmailService emailService;
    private final RegistrationTokenService registrationTokenService;
    private final UserRepository userRepository;

    private static final String subject = "Подтверждение регистрации";

    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        val username = event.getUsername();
        val email = event.getEmail();
        val url = event.getAppUrl();
        val user = userRepository.findByUsername(username).orElseThrow(); //TODO throw exception
        val token = registrationTokenService.createToken(user.getUsername()).getToken();
        val message = url + "/confirmRegister?token=" + token;
        emailService.sendSimpleMessage(email, subject, message);
    }

}
