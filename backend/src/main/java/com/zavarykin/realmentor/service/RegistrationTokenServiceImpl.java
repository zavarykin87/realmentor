package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.RegistrationTokenEntity;
import com.zavarykin.realmentor.exception.ObjectNotFoundException;
import com.zavarykin.realmentor.repository.RegistrationTokenRepository;
import com.zavarykin.realmentor.service.RegistrationTokenService;
import com.zavarykin.realmentor.service.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RegistrationTokenServiceImpl implements RegistrationTokenService {

    private final RegistrationTokenRepository registrationTokenRepository;
    private final UserService userService;

    @Override
    public RegistrationTokenEntity createToken(String username) {
        val user = userService.getByUsername(username);
        val token = UUID.randomUUID().toString();
        return registrationTokenRepository.save(new RegistrationTokenEntity(token, user));
    }

    @Override
    public RegistrationTokenEntity getByToken(String token) {
        return registrationTokenRepository.findByToken(token).orElseThrow(() -> new ObjectNotFoundException(token));
    }

    @Override
    public void deleteToken(RegistrationTokenEntity entity) {
        registrationTokenRepository.delete(entity);
    }
}
