package br.com.skillswap.AuthService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.skillswap.AuthService.exception.EmailAlreadyExistsException;
import br.com.skillswap.AuthService.exception.UsernameAlreadyExistsException;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.service.UserService;

/**
 * UserController
 */

 @RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        System.out.println("O usuário é: " + user.getUsername() + "A senha é: " + user.getPassword());

        boolean emailExists = userService.findByEmail(user.getEmail()).isPresent();
        boolean usernameExists = userService.findByUsername(user.getUsername()).isPresent();

        if(emailExists) {
            throw new EmailAlreadyExistsException("O email já foi usado. Por favor, escolha outro email.");
        } else if (usernameExists) {
            throw new UsernameAlreadyExistsException("O username já foi usado. Por favor, escolha outro username.");
        }
        

        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    
}