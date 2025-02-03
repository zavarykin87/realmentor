package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.entity.MentorEntity;

import java.util.List;

public interface MentorService {

    MentorEntity createMentor(MentorDto mentorDto);

    List<MentorDto> getAllMentors();

    List<MentorDto> findByFilter(List<String> specialization, List<String> skills, Integer experience,
                                 String company, String jobTitle);

}
