package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserEntity;

public interface UserService {
    UserEntity create();
    void delete(UserEntity userEntity);
}
