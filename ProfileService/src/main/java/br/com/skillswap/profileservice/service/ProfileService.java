package br.com.skillswap.profileservice.service;

import br.com.skillswap.common.dto.RabbitProfileDTO;
import br.com.skillswap.profileservice.dto.ProfileUpdateDTO;
import br.com.skillswap.profileservice.model.Address;
import br.com.skillswap.profileservice.model.Profile;
import br.com.skillswap.profileservice.repository.AddressRepository;
import br.com.skillswap.profileservice.repository.ProfileRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }


    public void createProfile(RabbitProfileDTO profileDTO) {
        Profile profile = new Profile(profileDTO);
        profile.setUpdatedAt(LocalDateTime.now());
        profileRepository.save(profile);
    }
    public Profile updateProfile(ProfileUpdateDTO profileUpdateDTO) {
        Optional<Profile> existingProfileOpt = profileRepository.findById(profileUpdateDTO.getProfileId());
        Profile profile;

        if (existingProfileOpt.isPresent()) {
            profile = existingProfileOpt.get();
            // Atualiza o perfil existente
            profile.setFirstName(profileUpdateDTO.getFirstName());
            profile.setLastName(profileUpdateDTO.getLastName());
            profile.setUserId(profileUpdateDTO.getUserId());
            profile.setBio(profileUpdateDTO.getBio());
        } else {
            // Cria um novo perfil se não existir
            profile = new Profile(profileUpdateDTO);
        }

        Address address;
        if (profile.getAddressId() != null) {
            // Atualiza o endereço existente
            address = profile.getAddressId();
            address.setStreet(profileUpdateDTO.getStreet());
            address.setNumber(profileUpdateDTO.getNumber());
            address.setZipCode(profileUpdateDTO.getZipCode());
            address.setState(profileUpdateDTO.getState());
            address.setCountry(profileUpdateDTO.getCountry());
            addressRepository.save(address); // Salva as alterações no endereço
        } else {
            // Cria um novo endereço
            address = new Address(profileUpdateDTO);
            Address addressSaved = addressRepository.save(address);
            profile.setAddressId(addressSaved); // Define o novo endereço para o perfil
        }


        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }

}
