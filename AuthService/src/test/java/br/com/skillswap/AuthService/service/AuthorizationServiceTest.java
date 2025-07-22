package br.com.skillswap.AuthService.service;

import br.com.skillswap.AuthService.dto.AuthenticationDTO;
import br.com.skillswap.AuthService.model.Enum.UserRoles;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorizationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    public void setup() {
        boolean emailExists = userRepository.findByEmail("testuser@example.com").isPresent();
        if (!emailExists) {
            testUser = new User();
            testUser.setEmail("testuser@example.com");
            testUser.setUsername("testuser");
            testUser.setPassword(passwordEncoder.encode("password123"));
            testUser.setRole(UserRoles.USER);
            userRepository.save(testUser);
        } else {
            testUser = userRepository.findByEmail("testuser@example.com").get();
        }
    }

    @AfterAll
    public void cleanup() {
        boolean emailExists = userRepository.findByEmail("testuser@example.com").isPresent();
        if (emailExists) {
            userRepository.delete(testUser);
        }
    }

    @Test
    public void testLoginSuccess() throws Exception {
        AuthenticationDTO authDTO = new AuthenticationDTO("testuser@example.com", "password123");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
                .andExpect(jsonPath("$.expiresIn").value(14 * 60))
                .andExpect(jsonPath("$.refreshExpiresIn").value(7 * 24 * 60 * 60))
                .andExpect(jsonPath("$.role").isNotEmpty());
    }

    @Test
    public void testWrongPassword() throws Exception {
        AuthenticationDTO authDTO = new AuthenticationDTO("testuser@example.com", "wrongpass");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Email ou senha inválidos."));
    }

    @Test
    public void testWrongEmail() throws Exception {
        AuthenticationDTO authDTO = new AuthenticationDTO("testuse2r@example.com", "password123");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Email ou senha inválidos."));
    }
}
