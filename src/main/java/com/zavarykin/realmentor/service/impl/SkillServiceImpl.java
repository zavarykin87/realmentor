package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.entity.SkillEntity;
import com.zavarykin.realmentor.repository.SkillRepository;
import com.zavarykin.realmentor.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<String> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skill -> skill.getName())
                .collect(Collectors.toList());
    }

    @Override
    public Set<SkillEntity> getAllByName(List<String> names) {
        Set<SkillEntity> skills = new HashSet<>();
        for (String name : names) {
            Optional<SkillEntity> optionalSkill = skillRepository.findById(name);
            if (optionalSkill.isPresent()) {
                skills.add(optionalSkill.get());
            } else {
                SkillEntity entity = new SkillEntity();
                entity.setName(name);
                skillRepository.save(entity);
                skills.add(entity);
            }
        }
        return skills;
    }
}
