package br.com.skillswap.AuthService.exception;

import br.com.skillswap.AuthService.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(AuthenticationException ex) {
        String errorMessage;

        if (ex instanceof BadCredentialsException) {
            errorMessage = "Email ou senha inválidos.";
        } else {
            errorMessage = "Erro de autenticação. Tente novamente.";
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(errorMessage));
    }
}
