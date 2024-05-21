package br.com.skillswap.AuthService.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "Campo username não pode estar vazio")
    @Size(min = 3, max = 50, message = "Username deve ser entre 3 e 50 caracteres")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Campo email não pode estar vazio")
    @Email(message = "Deve ser um email válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo de senha não pode ser vazio")
    @Size(min = 8, message = "Password deve ter pelo menos 8 caracteres")
    @Column(nullable = false, name="password_hash")
    private String password;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name="last_login")
    private LocalDateTime lastLogin;

}
