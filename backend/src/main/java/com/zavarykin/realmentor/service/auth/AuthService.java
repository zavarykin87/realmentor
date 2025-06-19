package com.zavarykin.realmentor.service.auth;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.exception.ArgumentInvalidException;
import com.zavarykin.realmentor.exception.EmailAlreadyExistsException;
import com.zavarykin.realmentor.exception.UsernameAlreadyExistsException;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.util.ArgumentChecker;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        val userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }

    public void signUp(final String username, final String password, final String email) {
        if (!ArgumentChecker.checkUserLogin(username)) {
            throw new ArgumentInvalidException("login");
        }
        if (!ArgumentChecker.checkUserPassword(password)) {
            throw new ArgumentInvalidException("password");
        }
        if (!ArgumentChecker.checkUserEmail(email)) {
            throw new ArgumentInvalidException("email");
        }
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        val user = new UserEntity(username, passwordEncoder.encode(password), email);
        userRepository.save(user);
    }

}
