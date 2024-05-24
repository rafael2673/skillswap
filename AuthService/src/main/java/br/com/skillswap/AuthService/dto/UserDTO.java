package br.com.skillswap.AuthService.dto;

import java.time.LocalDateTime;

import br.com.skillswap.AuthService.model.User;
import lombok.Data;


@Data

public class UserDTO {

    public UserDTO(){}
    
    public UserDTO(String username, String email, LocalDateTime registrationDate, LocalDateTime lastLogin) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.registrationDate = user.getRegistrationDate();
        this.lastLogin = user.getLastLogin();
    }

    private String username;

    private String email;
    
    private LocalDateTime registrationDate;
    
    private LocalDateTime lastLogin;

}
