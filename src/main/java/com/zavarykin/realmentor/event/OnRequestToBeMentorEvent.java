package com.zavarykin.realmentor.event;

import com.zavarykin.realmentor.dto.MentorDto;
import org.springframework.context.ApplicationEvent;

import java.time.OffsetDateTime;

public class OnRequestToBeMentorEvent extends ApplicationEvent {

    private MentorDto mentorDto;

    public OnRequestToBeMentorEvent(Object source, MentorDto mentorDto) {
        super(source);
        this.mentorDto = mentorDto;
    }

    public MentorDto getMentorDto() {
        return mentorDto;
    }

    public void setMentorDto(MentorDto mentorDto) {
        this.mentorDto = mentorDto;
    }

}
