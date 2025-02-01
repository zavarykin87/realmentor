package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.entity.SkillEntity;

import java.util.List;
import java.util.Set;

public interface SkillService {

    List<String> getAllSkills();

    Set<SkillEntity> getAllByName(List<String> names);
}
