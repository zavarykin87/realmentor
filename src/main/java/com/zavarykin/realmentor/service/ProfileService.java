package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.ProfileDto;

public interface ProfileService {

    ProfileDto getByUsername(String username);

    ProfileDto create(ProfileDto dto);
}
