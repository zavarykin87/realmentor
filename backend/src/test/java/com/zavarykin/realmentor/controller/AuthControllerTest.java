package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @MockitoBean
    private EmailServiceImpl emailService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void authenticateUser_shouldReturnTokenAndStatusOk() throws Exception {
        var userEntity = new UserEntity("user", passwordEncoder.encode("password"), "user@example.com");
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        String requestBody = """
        {
            "username": "user",
            "password": "password"
        }
        """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());

    }

    @Test
    void authenticateUser_shouldReturnForbiddenWhenUserNotEnabled() throws Exception {
        var userEntity = new UserEntity("user", passwordEncoder.encode("password"), "user@example.com");
        userRepository.save(userEntity);

        String requestBody = """
        {
            "username": "user",
            "password": "password"
        }
        """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isForbidden());

    }

    @Test
    void authenticateUser_shouldReturnForbiddenWhenPasswordInvalid() throws Exception {
        var userEntity = new UserEntity("user", passwordEncoder.encode("password"), "user@example.com");
        userRepository.save(userEntity);

        String requestBody = """
        {
            "username": "user",
            "password": "!password"
        }
        """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isForbidden());

    }

    @Test
    void registerUser_shouldRegisterUserSendMessageAndReturnOk() throws Exception {
        String requestBody = """
        {
            "username": "user",
            "password": "user",
            "email": "user@example.com"
        }
        """;

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(emailService).sendSimpleMessage(eq("user@example.com"), eq("Подтверждение регистрации"), anyString());
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenUsernameExists() throws Exception {
        String requestBody = """
        {
            "username": "user",
            "password": "user",
            "email": "user@example.com"
        }
        """;

        userRepository.save(new UserEntity(
                "user", passwordEncoder.encode("password"), "user@mail.ru"));

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Пользователь с таким логином: user уже существует"));
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenEmailExists() throws Exception {
        String requestBody = """
        {
            "username": "user",
            "password": "user",
            "email": "user@example.com"
        }
        """;

        userRepository.save(new UserEntity(
                "user2", passwordEncoder.encode("password"), "user@example.com"));

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Пользователь с таким email: user@example.com уже существует"));
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenLoginInvalid() {
        //TODO
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenEmailInvalid() {
        //TODO
    }

    @Test
    void confirmRegister() {
    }
}