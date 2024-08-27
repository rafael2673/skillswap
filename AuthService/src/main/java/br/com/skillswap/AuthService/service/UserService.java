package br.com.skillswap.AuthService.service;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.skillswap.AuthService.model.Enum.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.skillswap.AuthService.dto.UserDTO;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setRole(UserRoles.USER);
        userRepository.save(user);

        return new UserDTO(user);
    }

    public void changeLastLogin(User user) {
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
