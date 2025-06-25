package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.EmailService;
import com.zavarykin.realmentor.service.UserTokenService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {

    private final EmailService emailService;
    private final UserTokenService userTokenService;
    private final UserRepository userRepository;

    private static final String SUBJECT = "Подтверждение регистрации";
    private static final String ERROR_MSG = "Пользователь с таким email %s не существует";

    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        val email = event.getEmail();
        val url = event.getAppUrl();
        val user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(ERROR_MSG, email)));
        val token = userTokenService.createToken(user.getUsername()).getToken();
        val message = url + "/auth/confirmRegister?token=" + token;
        emailService.sendSimpleMessage(email, SUBJECT, message);
    }

}
