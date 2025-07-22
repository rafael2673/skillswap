package br.com.skillswap.AuthService.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


import br.com.skillswap.AuthService.dto.RegistrationDTO;
import br.com.skillswap.AuthService.model.Enum.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    public User(RegistrationDTO registrationDTO) {
        this.username = registrationDTO.getUsername();
        this.email = registrationDTO.getEmail();
        this.password = registrationDTO.getPassword();
    }

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

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles role;


    /**
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }
}
