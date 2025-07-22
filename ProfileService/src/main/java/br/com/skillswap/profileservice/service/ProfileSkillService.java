package br.com.skillswap.profileservice.service;

import br.com.skillswap.profileservice.dto.ProfileSkillDTO;
import br.com.skillswap.profileservice.exception.ResourceNotFoundException;
import br.com.skillswap.profileservice.model.Profile;
import br.com.skillswap.profileservice.model.ProfileSkill;
import br.com.skillswap.profileservice.model.Skill;
import br.com.skillswap.profileservice.repository.ProfileRepository;
import br.com.skillswap.profileservice.repository.ProfileSkillRepository;
import br.com.skillswap.profileservice.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ProfileSkillService {
    @Autowired
    private ProfileSkillRepository profileSkillRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillRepository skillRepository;

    public ProfileSkill createProfileSkill(ProfileSkillDTO profileSkillDTO) {
        ProfileSkill profileSkill = new ProfileSkill();
        profileSkill.setRoleInSkill(profileSkillDTO.getRoleInSkill());
        profileSkill.setProficiencyLevel(profileSkillDTO.getProficiencyLevel());
        profileSkill.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        profileSkill.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // Set Profile
        Profile profile = profileRepository.findById(profileSkillDTO.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        profileSkill.setProfile(profile);

        // Set Skill
        Skill skill = skillRepository.findById(profileSkillDTO.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        profileSkill.setSkill(skill);

        return profileSkillRepository.save(profileSkill);
    }

    public void deleteProfileSkill(Long id) {
        if (!profileSkillRepository.existsById(id)) {
            throw new ResourceNotFoundException("ProfileSkill not found with id: " + id);
        }
        profileSkillRepository.deleteById(id);
    }
}
