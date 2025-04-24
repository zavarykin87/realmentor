package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.RequestOnMentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestOnMentorRepository extends JpaRepository<RequestOnMentorEntity, Long> {

    List<RequestOnMentorEntity> findAllByApproveIsFalse();
}
