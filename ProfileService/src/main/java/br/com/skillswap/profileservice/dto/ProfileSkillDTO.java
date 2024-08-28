package br.com.skillswap.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileSkillDTO {
    private String roleInSkill;
    private String proficiencyLevel;
    private Long profileId;
    private Long skillId;

}

