package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.UserDto;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public String userPage(@PathVariable String username, Model model) {
        final UserDto userDto = userService.getByUsername(username);
        model.addAttribute("userDto", userDto);
        return "user";
    }

    @PostMapping("/user")
    public String user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return "redirect:/user/" + username;
    }
}
