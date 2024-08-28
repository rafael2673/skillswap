package br.com.skillswap.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerProfileSkillViewDTO {
    private String firstName;
    private String lastName;
    private String bio;
    private String pictureUrl;
    private String skillName;
    private String roleInSkill;
    private String proficiencyLevel;

}
