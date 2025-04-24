package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, String> {

    VerificationTokenEntity findByUserEntity(UserEntity user);

    VerificationTokenEntity findByToken(String token);

}
