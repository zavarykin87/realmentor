package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.entity.EmailEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void createTest() {
        UserEntity userEntity = userService.create();

        Optional<UserEntity> user1 = userRepository.findByLogin("andrew");
        Optional<UserEntity> user2 = userRepository.findById(userEntity.getId());

        //EmailEntity email = user1.get().getEmail();

        //EmailEntity email2 = user2.get().getEmail();

        assertTrue(user1 != null);
    }
}