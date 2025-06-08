package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.RequestOnMentorDto;
import com.zavarykin.realmentor.entity.AuthorityEntity;
import com.zavarykin.realmentor.entity.MentorEntity;
import com.zavarykin.realmentor.entity.RequestOnMentorEntity;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.MentorRepository;
import com.zavarykin.realmentor.repository.RequestOnMentorRepository;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.RequestOnMentorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestOnMentorServiceImpl implements RequestOnMentorService {

    private final RequestOnMentorRepository requestOnMentorRepository;
    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;


    public RequestOnMentorServiceImpl(RequestOnMentorRepository requestOnMentorRepository,
                                      MentorRepository mentorRepository,
                                      UserRepository userRepository) {
        this.requestOnMentorRepository = requestOnMentorRepository;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<RequestOnMentorDto> getAllNoApproved() {
        List<RequestOnMentorEntity> entities = requestOnMentorRepository.findAllByApproveIsFalse();
        return entities.stream().map(e -> mapEntityToDto.apply(e)).collect(Collectors.toList());
    }

    @Override
    public void approve(Long id) {
        RequestOnMentorEntity requestOnMentor = requestOnMentorRepository.findById(id).orElseThrow();
        MentorEntity mentorEntity = mentorRepository.findByUsername(requestOnMentor.getUsername()).orElseThrow();
        UserEntity userEntity = userRepository.findByUsername(mentorEntity.getUsername()).orElseThrow();
        requestOnMentor.setApprove(true);
        mentorEntity.setConfirm(true);
        //userEntity.addAuthority(new AuthorityEntity("ROLE_MENTOR", userEntity));
        requestOnMentorRepository.save(requestOnMentor);
        mentorRepository.save(mentorEntity);
        userRepository.save(userEntity);
    }

    private final Function<RequestOnMentorEntity, RequestOnMentorDto> mapEntityToDto = entity -> {
        RequestOnMentorDto dto = new RequestOnMentorDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setDateTime(entity.getDateTime());
        dto.setInfo(entity.getInfo());
        dto.setApprove(entity.isApprove());
        return dto;
    };
}
