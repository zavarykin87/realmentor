package com.zavarykin.realmentor.service.auth;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.event.OnRegistrationEvent;
import com.zavarykin.realmentor.exception.EmailAlreadyExistsException;
import com.zavarykin.realmentor.exception.UsernameAlreadyExistsException;
import com.zavarykin.realmentor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signIn(final String username, final String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    public void signUp(final String username, final String password, final String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
        //TODO checkPasswordValid
        //TODO checkEmailValid

        UserEntity user = new UserEntity(username, passwordEncoder.encode(password), email);
        userRepository.save(user);
    }

}
