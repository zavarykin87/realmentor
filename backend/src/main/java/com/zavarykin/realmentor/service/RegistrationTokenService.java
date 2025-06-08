package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.RegistrationTokenEntity;

public interface RegistrationTokenService {

    RegistrationTokenEntity createToken(String username);
    RegistrationTokenEntity getByUsername(String username);
    RegistrationTokenEntity getByToken(String token);
    void deleteToken(RegistrationTokenEntity entity);
}
