package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MentorEntity;
import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class MentorRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private UserRepository userRepository;

    private UserEntity user;
    private ProfileEntity profile;
    private MentorEntity mentor;

    @BeforeEach
    public void initEntities() {
        user = userRepository.findByUsername("admin").get();
        profile = new ProfileEntity();
        profile.setUserEntity(user);
        profile.setFirstname("admin");
        profile.setLastname("admin");
        testEntityManager.persist(profile);

        mentor = new MentorEntity();
        mentor.setProfileEntity(profile);
        mentor.setAbout("about me information");
        mentor.setJobTitle("java developer");
        mentor.setCompany("google");
        mentor.setExperience(10);
        testEntityManager.persist(mentor);

    }

    @Test
    @DisplayName("findByUsername_ok")
    public void findByUsername_ok() {
        assertEquals(true, mentorRepository.findByUsername("admin").isPresent());
    }

}