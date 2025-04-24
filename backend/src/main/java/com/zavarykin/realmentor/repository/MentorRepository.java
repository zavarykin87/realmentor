package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MentorEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<MentorEntity, String>, JpaSpecificationExecutor<MentorEntity> {

    Optional<MentorEntity> findByUsername(String username);

    List<MentorEntity> findAllByConfirmIsTrue();

}
