package com.zavarykin.realmentor.controller;

import com.zavarykin.realmentor.entity.UserEntity;
import com.zavarykin.realmentor.repository.UserRepository;
import com.zavarykin.realmentor.repository.UserTokenRepository;
import com.zavarykin.realmentor.service.UserTokenService;
import com.zavarykin.realmentor.service.EmailServiceImpl;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private UserTokenRepository tokenRepository;

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
            "password": "Password!123",
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
            "password": "Password!123",
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
            "password": "Password!123",
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
    void registerUser_shouldReturnServerErrorWhenLoginInvalid() throws Exception {
        String requestBody = """
        {
            "username": "!@#$%^&&",
            "password": "Password!123",
            "email": "user@example.com"
        }
        """;

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Недопустимое значение в параметре login"));
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenPasswordInvalid() throws Exception {
        String requestBody = """
        {
            "username": "user",
            "password": "123",
            "email": "user@example.com"
        }
        """;

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Недопустимое значение в параметре password"));
    }

    @Test
    void registerUser_shouldReturnServerErrorWhenEmailInvalid() throws Exception {
        String requestBody = """
        {
            "username": "user",
            "password": "Password!123",
            "email": "user@example"
        }
        """;

        mockMvc.perform(post("/register")
                        .header("Origin", "http://localhost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Недопустимое значение в параметре email"));
    }

    @Test
    void confirmRegister_shouldConfirmRegisterAndRedirectToIndexPage() throws Exception {
        var user = userRepository.save(new UserEntity("user", passwordEncoder.encode("password"), "email@example.com"));
        val tokenEntity = userTokenService.createToken(user.getUsername());

        assertFalse(user.isEnabled());

        mockMvc.perform(get("/confirmRegister")
                .header("Origin", "http://localhost")
                .param("token", tokenEntity.getToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        user = userRepository.findByUsername(user.getUsername()).get();

        assertTrue(user.isEnabled());
    }

    @Test
    void confirmRegister_shouldReturnServerErrorWhenTokenNotFound() throws Exception {
        var token = "testtoken";
        mockMvc.perform(get("/confirmRegister")
                        .header("Origin", "http://localhost")
                        .param("token", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("Объект testtoken не найден"));
    }

    @Test
    void confirmRegister_shouldReturnServerErrorWhenTokenExpired() throws Exception {
        var user = userRepository.save(new UserEntity("user", passwordEncoder.encode("password"), "email@example.com"));
        val tokenEntity = userTokenService.createToken(user.getUsername());
        tokenEntity.setExpiryDate(LocalDateTime.now().minusHours(24l));
        tokenRepository.save(tokenEntity);
        assertFalse(user.isEnabled());

        mockMvc.perform(get("/confirmRegister")
                        .header("Origin", "http://localhost")
                        .param("token", tokenEntity.getToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("Срок действия токена истек"));

        user = userRepository.findByUsername(user.getUsername()).get();
        assertFalse(user.isEnabled());
    }

    @Test
    void restorePassword_shouldCreateUserTokenAndSendMessage() throws Exception {
        var email = "user@example.com";
        var user = userRepository.save(new UserEntity("user", passwordEncoder.encode("password"), email));
        assertFalse(tokenRepository.findByUserEntity(user).isPresent());


        mockMvc.perform(post("/restorePassword")
                .header("Origin", "http://localhost")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertTrue(tokenRepository.findByUserEntity(user).isPresent());
        verify(emailService).sendSimpleMessage(eq(email), eq("Восстановление пароля"), anyString());
    }

    @Test
    void restorePassword_shouldReturnServerErrorWhenEmailNotFound() throws Exception {
        var email = "user@example.com";

        mockMvc.perform(post("/restorePassword")
                        .header("Origin", "http://localhost")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("Объект user@example.com не найден"));
    }

}