package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.ProfileDto;
import com.zavarykin.realmentor.dto.UserDto;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.entity.VerificationTokenEntity;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.exception.EntityNotFoundException;
import com.zavarykin.realmentor.service.ProfileService;
import com.zavarykin.realmentor.service.UserService;
import com.zavarykin.realmentor.service.VerificationTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationTokenService verificationTokenService;

    public UserController(UserService userService,
                          ProfileService profileService,
                          ApplicationEventPublisher eventPublisher,
                          VerificationTokenService verificationTokenService) {
        this.userService = userService;
        this.profileService = profileService;
        this.eventPublisher = eventPublisher;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping("/user/{username}")
    public String userPage(@PathVariable String username, Model model) throws EntityNotFoundException {
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
        ProfileDto profileDto = profileService.getByUsername(username);
        model.addAttribute("profileDto", profileDto);
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
    public String registration(UserDto userDto, HttpServletRequest request, Model model) {
        try {
            userService.create(userDto);
            String appUrl = request.getHeader("Origin");
            eventPublisher.publishEvent(new OnRegistrationEvent(appUrl, userDto.getUsername(), userDto.getEmail()));
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "login";
        }
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @GetMapping("/user/registrationConfirm")
    public String registrationConfirm(@RequestParam("token") String token) {
        VerificationTokenEntity verificationToken = verificationTokenService.getByToken(token);
        UserEntity userEntity = verificationToken.getUserEntity();
        userEntity.setEnabled(true);
        userService.saveUser(userEntity);
        return "redirect:/login";
    }

}
