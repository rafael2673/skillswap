package br.com.skillswap.AuthService.controller;

import br.com.skillswap.AuthService.dto.AuthenticationDTO;
import br.com.skillswap.AuthService.dto.LoginResponseDTO;
import br.com.skillswap.AuthService.service.TokenService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.skillswap.AuthService.dto.UserDTO;
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
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user) {

        boolean emailExists = userService.findByEmail(user.getEmail()).isPresent();
        boolean usernameExists = userService.findByUsername(user.getUsername()).isPresent();

        if (emailExists) {
            throw new EmailAlreadyExistsException("O email já foi usado. Por favor, escolha outro email.");
        } else if (usernameExists) {
            throw new UsernameAlreadyExistsException("O username já foi usado. Por favor, escolha outro username.");
        }


        UserDTO registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authentication(@Valid @RequestBody AuthenticationDTO user) {
        try {

            Authentication usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Erro na hora de autenticar", ex);
        }
    }


}