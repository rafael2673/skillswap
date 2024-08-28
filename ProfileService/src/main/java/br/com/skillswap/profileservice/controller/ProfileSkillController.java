package br.com.skillswap.profileservice.controller;

import br.com.skillswap.profileservice.dto.ProfileSkillDTO;
import br.com.skillswap.profileservice.model.ProfileSkill;
import br.com.skillswap.profileservice.service.ProfileSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile-skills")
public class ProfileSkillController {

    @Autowired
    private ProfileSkillService profileSkillService;

    @PostMapping
    public ResponseEntity<ProfileSkill> createProfileSkill(@RequestBody ProfileSkillDTO profileSkillDTO) {
        ProfileSkill createdProfileSkill = profileSkillService.createProfileSkill(profileSkillDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfileSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileSkill(@PathVariable Long id) {
        profileSkillService.deleteProfileSkill(id);
        return ResponseEntity.noContent().build();
    }
}

