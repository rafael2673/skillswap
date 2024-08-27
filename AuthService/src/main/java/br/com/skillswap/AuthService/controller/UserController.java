package br.com.skillswap.AuthService.controller;

import br.com.skillswap.AuthService.dto.*;
import br.com.skillswap.AuthService.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        User authenticatedUser = (User) auth.getPrincipal();

        String accessToken = tokenService.generateAccessToken(authenticatedUser);
        String refreshToken = tokenService.generateRefreshToken(authenticatedUser);

        long expiresIn = 15 * 60; // 15 minutos em segundos
        long refreshExpiresIn = 7 * 24 * 60 * 60; // 7 dias em segundos

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken, expiresIn, refreshExpiresIn));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshToken) {
        System.out.println(refreshToken);

        String email = tokenService.validateToken(refreshToken.refreshToken());

        if (!email.isEmpty() && userService.findByEmail(email).isPresent()) {
            User user = userService.findByEmail(email).get();
            String newAccessToken = tokenService.generateAccessToken(user);

            long expiresIn = 15 * 60; // 15 minutos em segundos
            long refreshExpiresIn = 7 * 24 * 60 * 60; // 7 dias em segundos

            return ResponseEntity.ok(new LoginResponseDTO(newAccessToken, refreshToken.refreshToken(), expiresIn, refreshExpiresIn));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }



}