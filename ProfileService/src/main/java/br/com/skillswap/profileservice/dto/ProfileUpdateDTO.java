package br.com.skillswap.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateDTO {
    private Long profileId;

    private String firstName;


    private String lastName;


    private String bio;


    private Long userId;


    private String street;


    private String number;


    private String zipCode;


    private String state;


    private String country;
}
