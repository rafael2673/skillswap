package br.com.skillswap.AuthService.controller;

import br.com.skillswap.AuthService.dto.AuthenticationDTO;
import br.com.skillswap.AuthService.dto.LoginResponseDTO;
import br.com.skillswap.AuthService.dto.RefreshTokenDTO;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.service.TokenService;
import br.com.skillswap.AuthService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authentication(@Valid @RequestBody AuthenticationDTO user) {

        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        User authenticatedUser = (User) auth.getPrincipal();

        userService.changeLastLogin(authenticatedUser);

        String accessToken = tokenService.generateAccessToken(authenticatedUser);
        String refreshToken = tokenService.generateRefreshToken(authenticatedUser);

        long expiresIn = 15 * 60; // 15 minutos em segundos
        long refreshExpiresIn = 7 * 24 * 60 * 60; // 7 dias em segundos

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken, expiresIn, refreshExpiresIn));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody RefreshTokenDTO refreshToken) {

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

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        // Lógica para validar o token
        String email = tokenService.validateToken(token);

        if (userService.findByEmail(email).isPresent()) {
            // Extrair ID do usuário do token ou retornar uma identificação apropriada
            User user = userService.findByEmail(email).get();
            return ResponseEntity.ok(user.getId().toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
