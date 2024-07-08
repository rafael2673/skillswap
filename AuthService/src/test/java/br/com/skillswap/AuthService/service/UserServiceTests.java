package br.com.skillswap.AuthService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.skillswap.AuthService.dto.UserDTO;
import br.com.skillswap.AuthService.model.User;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;
    

    @Test
    public void salvarUserComSucesso(){
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername("rafael26733");
        user.setLastLogin(LocalDateTime.now());

        UserDTO userDTO = userService.registerUser(user);

        UserDTO userDTO1 = new UserDTO(user);
        userDTO1.setRegistrationDate(userDTO.getRegistrationDate());

        assertEquals(userDTO1, userDTO);
    }

    @Test
    public void naoSalvarUserSemUsername() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername(""); 
        
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserSemEmail() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("");
        user.setPassword("teste123");
        user.setUsername("user12345");
        

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserEmailErrado() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafaelgmail.com");
        user.setPassword("teste123");
        user.setUsername("user12345");

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserUsernamePequeno() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername("us");
        
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }
    @Test
    public void naoSalvarUserSemSenha() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("");
        user.setUsername("user1234");
    

        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }
    @Test
    public void naoSalvarUserSenhaMenorQueOito() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("1234567");
        user.setUsername("user1234");
        
        assertThrows(ValidationException.class, () -> {
            userService.registerUser(user);
        });
    }

}
