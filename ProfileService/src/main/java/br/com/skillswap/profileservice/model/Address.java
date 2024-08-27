package br.com.skillswap.profileservice.model;


import br.com.skillswap.profileservice.dto.ProfileUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    public Address(ProfileUpdateDTO profileUpdateDTO) {
        this.street = profileUpdateDTO.getStreet();
        this.number = profileUpdateDTO.getNumber();
        this.zipCode = profileUpdateDTO.getZipCode();
        this.state = profileUpdateDTO.getState();
        this.country = profileUpdateDTO.getCountry();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    private String street;

    private String number;

    @Column(name = "zip_code")
    private String zipCode;


    private String state;


    private String country;

    @OneToOne(mappedBy = "addressId")
    @JsonIgnore
    private Profile profile;
}
