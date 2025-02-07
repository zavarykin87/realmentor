package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.RequestToMentoringDto;
import com.zavarykin.realmentor.entity.RequestToMentoringEntity;
import com.zavarykin.realmentor.repository.RequestToMentoringRepository;
import com.zavarykin.realmentor.service.RequestToMentoringService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestToMentoringServiceImpl implements RequestToMentoringService {

    private final RequestToMentoringRepository mentoringRepository;

    public RequestToMentoringServiceImpl(RequestToMentoringRepository mentoringRepository) {
        this.mentoringRepository = mentoringRepository;
    }

    @Override
    public void save(RequestToMentoringDto dto) {
        mentoringRepository.save(mapDtoToEntity.apply(dto));
    }

    @Override
    public List<RequestToMentoringDto> getAllByMentor(String mentorname) {
        return mentoringRepository.findAllByMentor(mentorname).stream()
                .map(entity -> mapEntityToDto.apply(entity))
                .collect(Collectors.toList());
    }

    // сделать проверку что ментор удаляет свои запросы
    @Override
    public void deleteById(Long id) {
        mentoringRepository.deleteById(id);
    }

    @Override
    public void approve(Long id) {
        // TODO
    }

    private final Function<RequestToMentoringDto, RequestToMentoringEntity> mapDtoToEntity = dto -> {
        RequestToMentoringEntity entity = new RequestToMentoringEntity();
        entity.setMentor(dto.getMentor());
        entity.setMentee(dto.getMentee());
        entity.setDateTime(LocalDateTime.parse(dto.getDateTime()));
        entity.setInfo(dto.getInfo());
        entity.setApprove(dto.isApprove());
        return entity;
    };

    private final Function<RequestToMentoringEntity, RequestToMentoringDto> mapEntityToDto = entity -> {
        RequestToMentoringDto dto = new RequestToMentoringDto();
        dto.setMentor(entity.getMentor());
        dto.setMentee(entity.getMentee());
        dto.setId(entity.getId());
        dto.setApprove(entity.isApprove());
        dto.setInfo(entity.getInfo());
        dto.setDateTime(entity.getDateTime().toString());
        return dto;
    };
}
