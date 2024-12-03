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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUsername = userDetails.getUsername();
        // должно быть отличие в view, когда пользователь заходит на свою или чужую страницу
        if (username.equals(currentUsername)) {
            model.addAttribute("owner", true);
        } else {
            model.addAttribute("owner", false);
        }
        UserDto userDto = userService.getByUsername(username);
        model.addAttribute("userDto", userDto);
        return "user";
    }

    /**
     * Метод вызывается после успешной аутентификации
     * @return редирект на домашнюю страницу пользователя
     */
    @PostMapping("/user")
    public String user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return "redirect:/user/" + username;
    }

    @GetMapping("/user/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registration(UserDto userDto) {
        userService.create(userDto);
        return "redirect:/login";
    }

}
