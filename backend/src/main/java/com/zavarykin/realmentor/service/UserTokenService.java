package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserTokenEntity;

public interface UserTokenService {

    UserTokenEntity createToken(String username);
    UserTokenEntity getByToken(String token);
    void deleteToken(UserTokenEntity entity);
}
