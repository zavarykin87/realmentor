package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, String> {

    Optional<UserTokenEntity> findByToken(String token);
    Optional<UserTokenEntity> findByUserEntity(UserEntity userEntity);

}
