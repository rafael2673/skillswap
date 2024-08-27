package br.com.skillswap.AuthService.service;

import br.com.skillswap.AuthService.dto.LoginResponseDTO;
import br.com.skillswap.AuthService.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${spring.security.token.secret}")
    private String secret;

    public String generateAccessToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("AuthService")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateAccessTokenExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw new RuntimeException("Error while creating access token", ex);
        }
    }

    public String generateRefreshToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("AuthService")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateRefreshTokenExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw new RuntimeException("Error while creating refresh token", ex);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("AuthService")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant generateAccessTokenExpirationDate(){
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant generateRefreshTokenExpirationDate(){
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }

}
