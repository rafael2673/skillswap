package br.com.skillswap.AuthService.service;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.skillswap.AuthService.messaging.MessageSender;
import br.com.skillswap.AuthService.model.Enum.UserRoles;
import br.com.skillswap.common.dto.RabbitProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.skillswap.AuthService.dto.UserDTO;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class UserService {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO registerUser(User user, String firstName, String lastName) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        user.setRole(UserRoles.USER);
        userRepository.save(user);

        RabbitProfileDTO profile = new RabbitProfileDTO(user.getId(), firstName, lastName);

        messageSender.sendProfileData(profile);

        return new UserDTO(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
