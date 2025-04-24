package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void findByUsername_ok() {
        UserEntity userEntity = userRepository.findByUsername("admin").get();
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setFirstname("admin");
        profileEntity.setLastname("admin");
        profileEntity.setUserEntity(userEntity);
        testEntityManager.persist(profileEntity);

        assertEquals(true, profileRepository.findById("admin").isPresent());
    }


}