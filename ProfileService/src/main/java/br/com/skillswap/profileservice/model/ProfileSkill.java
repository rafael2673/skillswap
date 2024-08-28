package br.com.skillswap.profileservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "profile_skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_in_skill", length = 7)
    private String roleInSkill;

    @Column(name = "proficiency_level", length = 12)
    private String proficiencyLevel;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skill skill;

}


