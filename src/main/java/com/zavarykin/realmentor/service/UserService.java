package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.UserTO;
import com.zavarykin.realmentor.entity.UserEntity;

public interface UserService {

    //UserEntity createUser(UserTO userTO);

    UserTO getByUsername(String username);

}
