package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.domain.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
