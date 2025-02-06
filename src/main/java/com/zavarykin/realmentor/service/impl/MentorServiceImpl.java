package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.entity.MentorEntity;
import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.repository.MentorRepository;
import com.zavarykin.realmentor.repository.ProfileRepository;
import com.zavarykin.realmentor.service.MentorService;
import com.zavarykin.realmentor.service.MentorSpecifications;
import com.zavarykin.realmentor.service.SkillService;
import com.zavarykin.realmentor.service.SpecializationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final SpecializationService specializationService;
    private final SkillService skillService;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;

    public MentorServiceImpl(MentorRepository mentorRepository,
                             SpecializationService specializationService,
                             SkillService skillService,
                             ProfileRepository profileRepository, EntityManager entityManager) {
        this.mentorRepository = mentorRepository;
        this.specializationService = specializationService;
        this.skillService = skillService;
        this.profileRepository = profileRepository;
        this.entityManager = entityManager;
    }

    @Override
    public MentorEntity createMentor(MentorDto dto) {
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

    @Override
    public List<MentorDto> getAllMentors() {
        return mentorRepository.findAllByConfirmIsTrue()
                .stream()
                .map(i -> mapEntityToDto.apply(i))
                .collect(Collectors.toList());
    }


    @Override
    public List<MentorDto> findByFilter(List<String> specialization, List<String> skills,
                                        Integer experience, String company, String jobTitle) {

        Specification<MentorEntity> spec = Specification
                .where(MentorSpecifications.isConfirm())
                .and(MentorSpecifications.hasSpecializationsName(specialization))
                .and(MentorSpecifications.hasSkillsName(skills))
                .and(MentorSpecifications.hasExperience(experience))
                .and(MentorSpecifications.hasCompany(company))
                .and(MentorSpecifications.hasJobTitle(jobTitle));

        return mentorRepository.findAll(spec).stream()
                .map(m -> mapEntityToDto.apply(m))
                .collect(Collectors.toList());
    }

    @Override
    public MentorDto getByUsername(String username) {
        Optional<MentorEntity> optional = mentorRepository.findByUsername(username);
        if (optional.isEmpty()) {
            return null;
        } else {
            return mapEntityToDto.apply(optional.get());
        }
    }

    private final Function<MentorEntity, MentorDto> mapEntityToDto = entity -> {
      MentorDto mentorDto = new MentorDto();
      mentorDto.setUsername(entity.getUsername());
      mentorDto.setCompany(entity.getCompany());
      mentorDto.setJobTitle(entity.getJobTitle());
      mentorDto.setExperience(entity.getExperience());
      mentorDto.setAbout(entity.getAbout());
      mentorDto.setSpecializations(entity.getSpecializations().stream().map(s -> s.getName()).collect(Collectors.toList()));
      mentorDto.setSkills(entity.getSkills().stream().map(s -> s.getName()).collect(Collectors.toList()));
      return mentorDto;
    };
}
