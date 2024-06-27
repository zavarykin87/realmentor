package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.entity.EmailEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity create() {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setAddress("andrew@mail.com");

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("andrew");
        userEntity.setPassword("12345");
        userEntity.setRegistrationDate(LocalDate.now());
        userEntity.setEmail(emailEntity);

        userEntity = userRepository.save(userEntity);
        return userEntity;

    }

    @Override
    public void delete(UserEntity userEntity) {

        userRepository.delete(userEntity);
    }

}
