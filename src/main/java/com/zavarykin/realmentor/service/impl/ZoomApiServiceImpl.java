package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.auth.ZoomAuthenticationHelper;
import com.zavarykin.realmentor.dto.MeetingDto;
import com.zavarykin.realmentor.service.MeetingService;
import com.zavarykin.realmentor.service.ZoomApiService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZoomApiServiceImpl implements ZoomApiService {

    private static final String ZOOM_API_URL = "https://api.zoom.us/v2/users/{userId}/meetings";

    private final ZoomAuthenticationHelper authenticationHelper;
    private final RestTemplate restTemplate;
    private final MeetingService meetingService;

    public ZoomApiServiceImpl(ZoomAuthenticationHelper authenticationHelper,
                              RestTemplate restTemplate,
                              MeetingService meetingService) {
        this.authenticationHelper = authenticationHelper;
        this.restTemplate = restTemplate;
        this.meetingService = meetingService;
    }

    @Override
    public void createMeeting(String mentor, String mentee, String datetime) throws Exception {
          RestTemplate restTemplate = new RestTemplate();;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticationHelper.getAuthenticationToken());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("topic", String.format("meeting for %s and %s", mentor, mentee));
        requestBody.put("type", 2);
        requestBody.put("start_time", datetime);
        requestBody.put("duration", 60);
        requestBody.put("timezone", "Europe/Moscow");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<MeetingDto> response = restTemplate.exchange(
                ZOOM_API_URL,
                HttpMethod.POST,
                requestEntity,
                MeetingDto.class,
                "zavarykin87@gmail.com" // временно
        );

        MeetingDto meetingDto = response.getBody();
        meetingDto.setMentor(mentor);
        meetingDto.setMentee(mentee);
        meetingDto.setHostEmail("zavarykin87@gmail.com"); // временно
        meetingDto.setTopic(String.format("meeting for %s and %s", mentor, mentee));

        meetingService.saveMeeting(meetingDto);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Вебинар успешно создан: " + response.getBody());
        } else {
            System.out.println("Ошибка при создании вебинара: " + response.getStatusCode());
        }
    }
}
