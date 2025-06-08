package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.RegistrationTokenEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationTokenRepository extends JpaRepository<RegistrationTokenEntity, String> {

    Optional<RegistrationTokenEntity> findByUserEntity(UserEntity user);

    Optional<RegistrationTokenEntity> findByToken(String token);

}
