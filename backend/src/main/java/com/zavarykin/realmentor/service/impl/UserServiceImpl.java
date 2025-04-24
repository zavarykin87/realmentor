package com.zavarykin.realmentor.service.impl;

import com.zavarykin.realmentor.dto.UserDto;
import com.zavarykin.realmentor.entity.EmailEntity;
import com.zavarykin.realmentor.entity.Role;
import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.exception.EmailAlreadyExistException;
import com.zavarykin.realmentor.exception.UserAlreadyExistException;
import com.zavarykin.realmentor.repository.EmailRepository;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailRepository emailRepository;

    public UserServiceImpl(UserRepository userRepository,
                           JdbcUserDetailsManager jdbcUserDetailsManager,
                           PasswordEncoder passwordEncoder,
                           EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.emailRepository = emailRepository;
    }

    @Override
    public UserDto getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setUsername(userEntity.getUsername());
        // TODO отрефакторить , т.к. email у юзера обязателен
        if (userEntity.getEmail() == null) {
            userDto.setEmail(null);
        } else {
            userDto.setEmail(userEntity.getEmail().getAddress());
        }
        return userDto;
    }

    @Override
    public void create(UserDto userDto) throws UserAlreadyExistException, EmailAlreadyExistException {
        checkEmail(userDto.getEmail());
        if (!jdbcUserDetailsManager.userExists(userDto.getUsername())) {
            UserDetails user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .roles(Role.USER.name())
                    .disabled(true)
                    .build();
            jdbcUserDetailsManager.createUser(user);
        } else {
            throw new UserAlreadyExistException();
        }
        saveEmail(userDto);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    private void checkEmail(String email) throws EmailAlreadyExistException {
        if (emailRepository.findByAddress(email).isPresent()) {
            throw new EmailAlreadyExistException();
        }
    }

    private void saveEmail(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername()).orElseThrow();
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setUserEntity(userEntity);
        emailEntity.setAddress(userDto.getEmail());
        emailRepository.save(emailEntity);
    }

}
