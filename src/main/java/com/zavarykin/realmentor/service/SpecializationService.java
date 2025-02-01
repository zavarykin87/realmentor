package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.SpecializationEntity;

import java.util.List;
import java.util.Set;

public interface SpecializationService {

    List<String> getAllSpecializations();

    Set<SpecializationEntity> getAllByName(List<String> names);
}
