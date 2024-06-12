package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
