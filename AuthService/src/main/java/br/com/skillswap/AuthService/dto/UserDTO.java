package br.com.skillswap.AuthService.dto;

import java.time.LocalDateTime;

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

    private String username;

    private String email;
    
    private LocalDateTime registrationDate;
    
    private LocalDateTime lastLogin;

}
