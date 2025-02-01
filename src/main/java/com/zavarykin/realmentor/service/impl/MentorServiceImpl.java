package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.entity.MentorEntity;
import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.repository.MentorRepository;
import com.zavarykin.realmentor.repository.ProfileRepository;
import com.zavarykin.realmentor.service.MentorService;
import com.zavarykin.realmentor.service.SkillService;
import com.zavarykin.realmentor.service.SpecializationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final SpecializationService specializationService;
    private final SkillService skillService;
    private final ProfileRepository profileRepository;

    public MentorServiceImpl(MentorRepository mentorRepository,
                             SpecializationService specializationService,
                             SkillService skillService,
                             ProfileRepository profileRepository) {
        this.mentorRepository = mentorRepository;
        this.specializationService = specializationService;
        this.skillService = skillService;
        this.profileRepository = profileRepository;
    }

    @Override
    public MentorEntity createOrUpdateMentor(MentorDto dto) {
        ProfileEntity profileEntity = profileRepository.findById(dto.getUsername()).orElseThrow();
        MentorEntity mentorEntity = mentorRepository.findByUsername(dto.getUsername()).orElse(new MentorEntity());
        mentorEntity.setProfileEntity(profileEntity);
        mentorEntity.setExperience(dto.getExperience());
        mentorEntity.setCompany(dto.getCompany());
        mentorEntity.setAbout(dto.getAbout());
        mentorEntity.setJobTitle(dto.getJobTitle());
        mentorEntity.setSpecializations(specializationService.getAllByName(dto.getSpecializations()));
        mentorEntity.setSkills(skillService.getAllByName(dto.getSkills()));
        mentorRepository.save(mentorEntity);
        return mentorEntity;
    }
}
