package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    ProfileEntity findByUserEntity(UserEntity userEntity);
}