package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SpecializationRepository extends JpaRepository<SpecializationEntity, String> {

    Set<SpecializationEntity> findAllByNameIn(List<String> names);
}
