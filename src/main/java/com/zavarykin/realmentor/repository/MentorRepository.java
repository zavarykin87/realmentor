package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<MentorEntity, String> {

    Optional<MentorEntity> findByUsername(String username);
}
