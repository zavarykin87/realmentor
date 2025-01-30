package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.EmailEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmailRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailRepository emailRepository;

    @Test
    public void saveEmail_ok() {
        UserEntity userEntity = userRepository.findByUsername("admin").get();
        EmailEntity email = new EmailEntity();
        email.setAddress("admin@mail.ru");
        email.setUserEntity(userEntity);

        testEntityManager.persist(email);

        Optional<EmailEntity> optionalEmail = emailRepository.findByAddress("admin@mail.ru");

        assertEquals(true, optionalEmail.isPresent());

    }

}