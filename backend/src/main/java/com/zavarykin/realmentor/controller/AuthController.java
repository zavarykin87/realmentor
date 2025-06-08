package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.AuthRequest;
import com.zavarykin.realmentor.dto.AuthResponse;
import com.zavarykin.realmentor.dto.RegisterRequest;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.service.RegistrationTokenService;
import com.zavarykin.realmentor.service.UserService;
import com.zavarykin.realmentor.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final ApplicationEventPublisher eventPublisher;
    private final RegistrationTokenService tokenService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        val token = authService.signIn(authRequest.username(), authRequest.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(HttpServletRequest request, @RequestBody RegisterRequest registerRequest) {
        authService.signUp(registerRequest.username(), registerRequest.password(), registerRequest.email());
        val appUrl = request.getHeader("Origin");
        eventPublisher.publishEvent(new OnRegistrationEvent(appUrl, registerRequest.username(), registerRequest.email()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirmRegister")
    public ResponseEntity<?> confirmRegister(@RequestParam String token) {
        val tokenEntity = tokenService.getByToken(token);
        val user = tokenEntity.getUserEntity();
        user.setEnabled(true);
        userService.saveUser(user);
        tokenService.deleteToken(tokenEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
