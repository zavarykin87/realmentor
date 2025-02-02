package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.dto.RequestOnMentorDto;
import com.zavarykin.realmentor.event.OnRequestToBeMentorEvent;
import com.zavarykin.realmentor.service.MentorService;
import com.zavarykin.realmentor.service.RequestOnMentorService;
import com.zavarykin.realmentor.service.SkillService;
import com.zavarykin.realmentor.service.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MentorController {

    private final SpecializationService specializationService;
    private final SkillService skillService;
    private final MentorService mentorService;
    private final ApplicationEventPublisher eventPublisher;
    private final RequestOnMentorService requestOnMentorService;

    public MentorController(SpecializationService specializationService,
                            SkillService skillService,
                            MentorService mentorService,
                            ApplicationEventPublisher eventPublisher,
                            RequestOnMentorService requestOnMentorService) {
        this.specializationService = specializationService;
        this.skillService = skillService;
        this.mentorService = mentorService;
        this.eventPublisher = eventPublisher;
        this.requestOnMentorService = requestOnMentorService;
    }

    @GetMapping("/mentor")
    public String mentorPage(Model model) {
        List<String> specializations = specializationService.getAllSpecializations();
        List<String> skills = skillService.getAllSkills();
        model.addAttribute("specializations", specializations);
        model.addAttribute("skills", skills );
        return "mentor";
    }

    @PostMapping("/mentor")
    public String createMentor(MentorDto mentorDto, String skills) {
        List<String> skillList = new ArrayList<>();
        String[] array = skills.split(",");
        for (int i = 0; i < array.length; i++) {
            skillList.add(array[i]);
        }
        mentorDto.setSkills(skillList);
        mentorService.createMentor(mentorDto);
        eventPublisher.publishEvent(new OnRequestToBeMentorEvent(mentorDto.getUsername(), mentorDto));
        return "mentor";
    }

    @GetMapping("/mentor/requests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String requestPage(Model model) {
        List<RequestOnMentorDto> requests = requestOnMentorService.getAllNoApproved();
        model.addAttribute("requests", requests);
        return "requestOnMentor";
    }

    @PostMapping("/mentor/requests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String approveRequest(@RequestParam(name = "ids") Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            requestOnMentorService.approve(ids[i]);
        }
        return "requestOnMentor";
    }

}
