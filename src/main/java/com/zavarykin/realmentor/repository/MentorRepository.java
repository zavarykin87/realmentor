package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<MentorEntity, Long> {
}
