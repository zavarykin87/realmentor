package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.UserEntity;

public interface UserService {

    UserEntity getByUsername(String username);
    void saveUser(UserEntity userEntity);
}
