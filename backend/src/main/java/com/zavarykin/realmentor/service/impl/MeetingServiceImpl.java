package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.MeetingDto;
import com.zavarykin.realmentor.entity.MeetingEntity;
import com.zavarykin.realmentor.repository.MeetingRepository;
import com.zavarykin.realmentor.service.MeetingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public MeetingEntity saveMeeting(MeetingDto meetingDto) {
        return meetingRepository.save(mapDtoToEntity.apply(meetingDto));
    }

    private final Function<MeetingDto, MeetingEntity> mapDtoToEntity = dto -> {
        MeetingEntity entity = new MeetingEntity();
        entity.setDuration(Integer.valueOf(dto.getDuration()));
        entity.setMeetingId(Long.valueOf(dto.getMeetingId()));
        entity.setHostEmail(dto.getHostEmail());
        entity.setMentee(dto.getMentee());
        entity.setMentor(dto.getMentor());
        entity.setJoin_url(dto.getJoin_url());
        entity.setStartUrl(dto.getStartUrl());
        entity.setTopic(dto.getTopic());
        entity.setStartDateTime(LocalDateTime.parse(dto.getStartDateTime(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        return entity;
    };
}
