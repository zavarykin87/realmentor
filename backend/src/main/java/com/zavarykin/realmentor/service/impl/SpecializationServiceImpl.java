package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.entity.SpecializationEntity;
import com.zavarykin.realmentor.repository.SpecializationRepository;
import com.zavarykin.realmentor.service.SpecializationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<String> getAllSpecializations() {
        return specializationRepository.findAll().stream()
                .map(spec -> spec.getName())
                .collect(Collectors.toList());
    }

    @Override
    public Set<SpecializationEntity> getAllByName(List<String> names) {
        return specializationRepository.findAllByNameIn(names);
    }
}
