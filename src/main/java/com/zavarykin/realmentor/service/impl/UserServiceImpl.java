package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.UserTO;
import com.zavarykin.realmentor.entity.Role;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserEntity createUser(UserTO userTO) {
        if (!userDetailsManager.userExists(userTO.getUsername())) {
            UserDetails user = User.builder()
                    .username(userTO.getUsername())
                    .password(userTO.getPassword())
                    .roles(Role.USER.name())
                    .build();
            userDetailsManager.createUser(user);
            return userRepository.findByUsername(userTO.getUsername()).get();
        } else {
            return null;
        }
    }
}
