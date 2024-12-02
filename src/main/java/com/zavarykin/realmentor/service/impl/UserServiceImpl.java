package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.UserDto;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsManager userDetailsManager;

    public UserServiceImpl(UserRepository userRepository, UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public UserDto getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setUsername(userEntity.getUsername());
        return userDto;
    }

    //    @Override
//    public UserTO createUser(UserTO userTO) {
//        if (!userDetailsManager.userExists(userTO.getUsername())) {
//            UserDetails user = User.builder()
//                    .username(userTO.getUsername())
//                    .password(userTO.getPassword())
//                    .roles(Role.USER.name())
//                    .build();
//            userDetailsManager.createUser(user);
//            return userRepository.findByUsername(userTO.getUsername()).get();
//        } else {
//            return null;
//        }
//    }
}
