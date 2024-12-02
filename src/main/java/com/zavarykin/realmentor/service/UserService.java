package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.UserDto;

public interface UserService {

    //UserEntity createUser(UserTO userTO);

    UserDto getByUsername(String username);

}
