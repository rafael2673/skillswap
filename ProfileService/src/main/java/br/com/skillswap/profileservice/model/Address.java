package br.com.skillswap.profileservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    private String state;

    @NotNull
    private String country;


}
