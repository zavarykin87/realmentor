package com.zavarykin.realmentor.repository;

import com.zavarykin.realmentor.entity.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<SpecializationEntity, SpecializationEntity.DerivedId> {
}
