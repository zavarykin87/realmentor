package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.RequestToMentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestToMentoringRepository extends JpaRepository<RequestToMentoringEntity, Long> {

    List<RequestToMentoringEntity> findAllByMentor(String mentor);
}
