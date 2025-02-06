package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.RequestToMentoringDto;
import com.zavarykin.realmentor.entity.RequestToMentoringEntity;
import com.zavarykin.realmentor.repository.RequestToMentoringRepository;
import com.zavarykin.realmentor.service.RequestToMentoringService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Function;

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

    private final Function<RequestToMentoringDto, RequestToMentoringEntity> mapDtoToEntity = dto -> {
        RequestToMentoringEntity entity = new RequestToMentoringEntity();
        entity.setMentor(dto.getMentor());
        entity.setMentee(dto.getMentee());
        entity.setDateTime(LocalDateTime.parse(dto.getDateTime()));
        entity.setInfo(dto.getInfo());
        entity.setApprove(dto.isApprove());
        return entity;
    };
}
