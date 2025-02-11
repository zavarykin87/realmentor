package com.zavarykin.realmentor.service;

import com.zavarykin.realmentor.dto.MeetingDto;
import com.zavarykin.realmentor.entity.MeetingEntity;

public interface MeetingService {

    MeetingEntity saveMeeting(MeetingDto meetingDto);
}
