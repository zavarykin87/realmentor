package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.dto.RequestOnMentorDto;

import java.util.List;

public interface RequestOnMentorService {

    List<RequestOnMentorDto> getAllNoApproved();

    void approve(Long id);
}
