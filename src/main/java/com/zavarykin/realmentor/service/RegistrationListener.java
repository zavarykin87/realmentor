package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {
    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        // удалить
    }

    //    private final EmailService emailService;
//    private final VerificationTokenService verificationTokenService;
//    private final UserRepository userRepository;
//
//    private static final String subject = "Подтверждение регистрации";
//
//    public RegistrationListener(EmailService emailService,
//                                VerificationTokenService verificationTokenService,
//                                UserRepository userRepository) {
//        this.emailService = emailService;
//        this.verificationTokenService = verificationTokenService;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void onApplicationEvent(OnRegistrationEvent event) {
//        String username = event.getUsername();
//        String email = event.getEmail();
//        String url = event.getAppUrl();
//        UserEntity user = userRepository.findByUsername(username).orElseThrow();
//
//        String token = verificationTokenService.createTokenForUser(user).getToken();
//
//        String message = url + "/user/registrationConfirm?token=" + token;
//
//        emailService.sendSimpleMessage(email, subject, message);
//    }

}
