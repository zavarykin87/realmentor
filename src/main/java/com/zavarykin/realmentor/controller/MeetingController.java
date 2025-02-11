package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.service.ZoomApiService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingController {

    private final ZoomApiService zoomApiService;

    public MeetingController(ZoomApiService zoomApiService) {
        this.zoomApiService = zoomApiService;
    }

//    @GetMapping("/meeting")
//    public String meetings() {
//
//        return "meeting";
//    }

//    @GetMapping("meeting/create")
//    @PreAuthorize("hasRole('ROLE_MENTOR')")
//    public String createMeeting() throws Exception {
//        zoomApiService.createMeeting();
//        return "redirect:/index";
//    }
}
