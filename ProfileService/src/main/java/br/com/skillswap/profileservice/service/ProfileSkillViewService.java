package br.com.skillswap.profileservice.service;

import br.com.skillswap.profileservice.dto.LearnerProfileSkillViewDTO;
import br.com.skillswap.profileservice.dto.TeacherProfileSkillViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileSkillViewService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LearnerProfileSkillViewDTO> getLearners() {
        String sql = "SELECT * FROM learner_profile_skill_view";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(LearnerProfileSkillViewDTO.class));
    }

    public List<TeacherProfileSkillViewDTO> getTeachers() {
        String sql = "SELECT * FROM teacher_profile_skill_view";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TeacherProfileSkillViewDTO.class));
    }
}
