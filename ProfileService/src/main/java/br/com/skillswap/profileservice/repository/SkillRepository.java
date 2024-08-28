package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
