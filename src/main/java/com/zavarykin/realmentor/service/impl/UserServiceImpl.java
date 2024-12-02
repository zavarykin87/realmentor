package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.UserDto;
import com.zavarykin.realmentor.entity.Role;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserDetailsManager userDetailsManager,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setUsername(userEntity.getUsername());
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (!userDetailsManager.userExists(userDto.getUsername())) {
            UserDetails user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .roles(Role.USER.name())
                    .build();
            userDetailsManager.createUser(user);
            return getByUsername(user.getUsername());
        } else {
            return null;
        }
    }

}
