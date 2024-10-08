package br.com.skillswap.AuthService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
