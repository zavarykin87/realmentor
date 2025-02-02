package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.RequestOnMentorDto;
import com.zavarykin.realmentor.entity.MentorEntity;
import com.zavarykin.realmentor.entity.RequestOnMentorEntity;
import com.zavarykin.realmentor.repository.MentorRepository;
import com.zavarykin.realmentor.repository.RequestOnMentorRepository;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestOnMentorServiceImpl implements RequestOnMentorService {

    private final RequestOnMentorRepository requestOnMentorRepository;
    private final MentorRepository mentorRepository;
    private final JdbcUserDetailsManager userDetailsManager;

    public RequestOnMentorServiceImpl(RequestOnMentorRepository requestOnMentorRepository,
                                      MentorRepository mentorRepository,
                                      JdbcUserDetailsManager userDetailsManager) {
        this.requestOnMentorRepository = requestOnMentorRepository;
        this.mentorRepository = mentorRepository;
        this.userDetailsManager = userDetailsManager;
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
        requestOnMentor.setApprove(true);
        mentorEntity.setConfirm(true);
        requestOnMentorRepository.save(requestOnMentor);
        mentorRepository.save(mentorEntity);
        // TODO добавить пользователю роль MENTOR
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
