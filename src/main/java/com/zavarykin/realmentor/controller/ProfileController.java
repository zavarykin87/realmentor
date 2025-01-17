package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.ProfileDto;
import com.zavarykin.realmentor.service.ProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @PostMapping("/profile")
    public String profile(ProfileDto profileDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        profileDto.setUsername(username);
        profileService.createOrUpdate(profileDto);
        return "redirect:/user/" + username;
    }
}
