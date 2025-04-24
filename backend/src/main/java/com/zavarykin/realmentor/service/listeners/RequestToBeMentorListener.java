package com.zavarykin.realmentor.service.listeners;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.entity.RequestOnMentorEntity;
import com.zavarykin.realmentor.event.OnRequestToBeMentorEvent;
import com.zavarykin.realmentor.repository.RequestOnMentorRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class RequestToBeMentorListener implements ApplicationListener<OnRequestToBeMentorEvent> {

    private final RequestOnMentorRepository repository;

    public RequestToBeMentorListener(RequestOnMentorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(OnRequestToBeMentorEvent event) {
        MentorDto mentorDto = event.getMentorDto();
        RequestOnMentorEntity entity = new RequestOnMentorEntity();
        entity.setUsername(mentorDto.getUsername());
        entity.setDateTime(OffsetDateTime.now());
        entity.setInfo(mentorDto.toString());
        repository.save(entity);
    }
}
