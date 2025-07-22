package br.com.skillswap.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileWithAddressDTO {
    private Long profileId;
    private String firstName;
    private String lastName;
    private String bio;
    private String pictureUrl;
    private LocalDateTime updatedAt;
    private Long userId;
    private Long addressId;
    private String street;
    private String number;
    private String zipCode;
    private String state;
    private String country;
}

