package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.model.ProfileSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileSkillRepository extends JpaRepository<ProfileSkill, Long> {
}
