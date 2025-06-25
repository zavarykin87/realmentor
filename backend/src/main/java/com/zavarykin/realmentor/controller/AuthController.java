package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.dto.AuthRequest;
import com.zavarykin.realmentor.dto.AuthResponse;
import com.zavarykin.realmentor.dto.RegisterRequest;
import com.zavarykin.realmentor.event.OnConfirmRegistrationEvent;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.event.OnRestorePasswordEvent;
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
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        val token = authService.signIn(authRequest.email(), authRequest.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(HttpServletRequest request, @RequestBody RegisterRequest registerRequest) {
        authService.signUp(registerRequest.email(), registerRequest.password());
        val appUrl = request.getHeader("Origin");
        eventPublisher.publishEvent(new OnRegistrationEvent(appUrl, registerRequest.email()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirmRegister")
    public ResponseEntity<?> confirmRegister(@RequestParam String token) {
        eventPublisher.publishEvent(new OnConfirmRegistrationEvent(token));
        val headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/restorePassword")
    public ResponseEntity<?> restorePassword(HttpServletRequest request, @RequestParam String email) {
        val appUrl = request.getHeader("Origin");
        eventPublisher.publishEvent(new OnRestorePasswordEvent(email, appUrl));
        return ResponseEntity.ok().build();
    }

    //TODO reset-password

}
