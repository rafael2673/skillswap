package br.com.skillswap.profileservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "profile")
public class Profile {

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

    @Column(name = "address_id")
    @OneToOne
    private Address addressId;

}
