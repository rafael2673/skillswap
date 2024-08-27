package br.com.skillswap.profileservice.model;

import br.com.skillswap.common.dto.RabbitProfileDTO;
import br.com.skillswap.profileservice.dto.ProfileUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class Profile {

    public Profile(RabbitProfileDTO profileDTO){
        this.firstName = profileDTO.getFirstName();
        this.lastName = profileDTO.getLastName();
        this.userId = profileDTO.getUserId();
    }

    public Profile(ProfileUpdateDTO profileUpdateDTO) {
        this.profileId = profileUpdateDTO.getProfileId();
        this.firstName = profileUpdateDTO.getFirstName();
        this.lastName = profileUpdateDTO.getLastName();
        this.userId = profileUpdateDTO.getUserId();
        this.bio = profileUpdateDTO.getBio();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @NotNull
    @Size(max = 40, message = "Nome deve ter no máximo 40 letras" )
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String bio;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address addressId;

}
