package br.com.skillswap.AuthService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.com.skillswap.AuthService.dto.UserDTO;
import br.com.skillswap.AuthService.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

public class UserServiceTests {

    private UserService userServiceMock = mock(UserService.class);
    
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void salvarUserComSucesso(){
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername("rafael26733");
        user.setRegistrationDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        UserDTO userDTO = new UserDTO(user);

            when(userServiceMock.registerUser(any(User.class)))
            .thenAnswer(invocation -> {
                User argumentUser = invocation.getArgument(0);
                Set<ConstraintViolation<User>> violations = validator.validate(argumentUser);
                if (violations.isEmpty()) {
                    return new UserDTO(argumentUser);
                } else {
                    throw new ValidationException("Usuário inválido");
                }
            });

        assertEquals(userServiceMock.registerUser(user), userDTO);
    }

    @Test
    public void salvarUserComSucessoSemErro(){
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername("rafael26733");
        user.setRegistrationDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        UserDTO userDTO = new UserDTO(user);

        when(userServiceMock.registerUser(user)).thenReturn(userDTO);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        assertTrue(violations.isEmpty());
        
    }

    @Test
    public void naoSalvarUserSemUsername() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername(""); 
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }
        
        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserSemEmail() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("");
        user.setPassword("teste123");
        user.setUsername("user12345");
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            
            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }

        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserEmailErrado() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafaelgmail.com");
        user.setPassword("teste123");
        user.setUsername("user12345");
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            
            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }

        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }

    @Test
    public void naoSalvarUserUsernamePequeno() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("teste123");
        user.setUsername("us");
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {

            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }

        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }
    @Test
    public void naoSalvarUserSemSenha() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("");
        user.setUsername("user1234");
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {

            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }

        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }
    @Test
    public void naoSalvarUserSenhaMenorQueOito() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("rafael@gmail.com");
        user.setPassword("1234567");
        user.setUsername("user1234");
        
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {

            doThrow(ValidationException.class).when(userServiceMock).registerUser(user);
        }

        assertThrows(ValidationException.class, () -> {
            userServiceMock.registerUser(user);
        });
    }

}
