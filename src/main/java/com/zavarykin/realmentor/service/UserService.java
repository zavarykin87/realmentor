package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.UserDto;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto getByUsername(String username);

}
