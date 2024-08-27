package br.com.skillswap.profileservice.controller;

import br.com.skillswap.profileservice.dto.ProfileUpdateDTO;
import br.com.skillswap.profileservice.model.Profile;
import br.com.skillswap.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Profile> getProfile() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = 0L;
        if (object instanceof String) {
            String stringToConvert = String.valueOf(object);
            userId = Long.parseLong(stringToConvert);
        }
        System.out.println(userId);
        Optional<Profile> profile = profileService.getProfileByUserId(userId);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(@RequestBody ProfileUpdateDTO profileUpdateDTO) {
        Profile updatedProfile = profileService.updateProfile(profileUpdateDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
