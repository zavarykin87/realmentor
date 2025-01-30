package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.AuthorityEntity;
import com.zavarykin.realmentor.entity.Role;
import com.zavarykin.realmentor.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsernameAdmin() {
        assertEquals(true, userRepository.findByUsername("admin").isPresent());
    }

    @Test
    void saveNewUser_ok() {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("password");
        user.addAuthority(new AuthorityEntity("ROLE_USER", "user"));
        user.addAuthority(new AuthorityEntity("ROLE_USER", "user")); // не должен сохраниться
        user.addAuthority(new AuthorityEntity("ROLE_MENTOR", "user"));
        testEntityManager.persist(user);
        assertEquals(2, userRepository.findByUsername("user").get().getAuthorities().size());
    }
}