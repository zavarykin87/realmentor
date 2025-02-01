package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.MentorDto;
import com.zavarykin.realmentor.service.MentorService;
import com.zavarykin.realmentor.service.SkillService;
import com.zavarykin.realmentor.service.SpecializationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MentorController {

    private final SpecializationService specializationService;
    private final SkillService skillService;
    private final MentorService mentorService;

    public MentorController(SpecializationService specializationService,
                            SkillService skillService,
                            MentorService mentorService) {
        this.specializationService = specializationService;
        this.skillService = skillService;
        this.mentorService = mentorService;
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
        mentorService.createOrUpdateMentor(mentorDto);
        return "mentor";
    }

}
