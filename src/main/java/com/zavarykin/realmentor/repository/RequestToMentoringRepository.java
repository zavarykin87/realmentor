package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.RequestToMentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestToMentoringRepository extends JpaRepository<RequestToMentoringEntity, Long> {
}
