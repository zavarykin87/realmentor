package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "roleEntities")
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
