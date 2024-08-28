package br.com.skillswap.AuthService.controller;

import br.com.skillswap.AuthService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.skillswap.AuthService.exception.EmailAlreadyExistsException;
import br.com.skillswap.AuthService.exception.UsernameAlreadyExistsException;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.service.UserService;
import jakarta.validation.Valid;


/**
 * UserController
 */

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegistrationDTO registration) {

        boolean emailExists = userService.findByEmail(registration.getEmail()).isPresent();
        boolean usernameExists = userService.findByUsername(registration.getUsername()).isPresent();

        if (emailExists) {
            throw new EmailAlreadyExistsException("O email já foi usado. Por favor, escolha outro email.");
        } else if (usernameExists) {
            throw new UsernameAlreadyExistsException("O username já foi usado. Por favor, escolha outro username.");
        }

        User user = new User(registration);
        UserDTO registeredUser = userService.registerUser(user, registration.getFirstName(), registration.getLastName());
        return ResponseEntity.ok(registeredUser);
    }



}