package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.dto.RequestOnMentorDto;
import com.zavarykin.realmentor.dto.RequestToMentoringDto;
import com.zavarykin.realmentor.event.OnRequestToBeMentorEvent;
import com.zavarykin.realmentor.service.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final RequestToMentoringService requestToMentoringService;

    public MentorController(SpecializationService specializationService,
                            SkillService skillService,
                            MentorService mentorService,
                            ApplicationEventPublisher eventPublisher,
                            RequestOnMentorService requestOnMentorService,
                            RequestToMentoringService requestToMentoringService) {
        this.specializationService = specializationService;
        this.skillService = skillService;
        this.mentorService = mentorService;
        this.eventPublisher = eventPublisher;
        this.requestOnMentorService = requestOnMentorService;
        this.requestToMentoringService = requestToMentoringService;
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

    @GetMapping("/mentors")
    public String getAllMentors(Model model) {
        List<MentorDto> mentors = mentorService.getAllMentors();
        List<String> specializations = specializationService.getAllSpecializations();
        List<String> skills = skillService.getAllSkills();
        model.addAttribute("specializations", specializations);
        model.addAttribute("skills", skills );
        model.addAttribute("mentors", mentors);
        return "mentors";
    }

    @PostMapping("/mentors")
    public String getFilteredMentors(@RequestParam(value = "specializations", required = false) List<String> specializations,
                                     @RequestParam(value = "skills", required = false) List<String> skills,
                                     @RequestParam(value = "experience", required = false) Integer experience,
                                     @RequestParam(value = "company", required = false) String company,
                                     @RequestParam(value = "jobTitle", required = false) String jobTitle,
                                     Model model) {
        List<MentorDto> mentors = mentorService.findByFilter(specializations, skills, experience, company,jobTitle);
        model.addAttribute("specializations", specializations);
        model.addAttribute("skills", skills );
        model.addAttribute("mentors", mentors);
        return "mentors";
    }

    @GetMapping("/requestToMentoring/{mentor}")
    public String requestToMentoringForm(@PathVariable String mentor, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mentee = userDetails.getUsername();
        model.addAttribute("mentor", mentor);
        model.addAttribute("mentee", mentee);
        return "requestToMentoring";
    }

    @PostMapping("/requestToMentoring")
    public String requestToMentoring(RequestToMentoringDto request) {
        requestToMentoringService.save(request);
        return "redirect:/user/" + request.getMentee();
    }

}
