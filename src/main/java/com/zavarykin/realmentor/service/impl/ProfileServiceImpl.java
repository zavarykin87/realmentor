package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.ProfileDto;
import com.zavarykin.realmentor.entity.ProfileEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.ProfileRepository;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository,
                              UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDto getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        ProfileEntity entity = profileRepository.findByUserEntity(userEntity);
        if (entity == null) {
            return null;
        } else {
            return mapEntityToDto.apply(entity);
        }
    }

    @Override
    public ProfileDto create(ProfileDto dto) {
        UserEntity userEntity = userRepository.findByUsername(dto.getUsername()).orElseThrow();
        ProfileEntity entity = new ProfileEntity();
        entity.setUserEntity(userEntity);
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity = profileRepository.save(entity);
        return mapEntityToDto.apply(entity);
    }

    private Function<ProfileEntity, ProfileDto> mapEntityToDto = entity -> {
      ProfileDto dto = new ProfileDto();
      dto.setId(entity.getId());
      dto.setUsername(entity.getUserEntity().getUsername());
      dto.setFirstname(entity.getFirstname());
      dto.setLastname(entity.getLastname());
      return dto;
    };
}
