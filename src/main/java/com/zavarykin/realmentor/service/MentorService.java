package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.entity.MentorEntity;

public interface MentorService {

    MentorEntity createOrUpdateMentor(MentorDto mentorDto);
}
