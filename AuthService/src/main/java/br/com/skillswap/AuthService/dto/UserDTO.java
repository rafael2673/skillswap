package br.com.skillswap.AuthService.dto;

import java.time.LocalDateTime;

import br.com.skillswap.AuthService.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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
