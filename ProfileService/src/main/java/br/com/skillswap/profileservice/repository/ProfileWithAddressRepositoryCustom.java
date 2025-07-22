package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.dto.ProfileWithAddressDTO;

import java.util.List;

public interface ProfileWithAddressRepositoryCustom {
    List<ProfileWithAddressDTO> findAllProfiles();
}
