package br.com.skillswap.profileservice.controller;

import br.com.skillswap.profileservice.dto.LearnerProfileSkillViewDTO;
import br.com.skillswap.profileservice.dto.TeacherProfileSkillViewDTO;
import br.com.skillswap.profileservice.service.ProfileSkillViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileSkillViewController {

    @Autowired
    private ProfileSkillViewService profileSkillViewService;

    @GetMapping("/learners")
    public ResponseEntity<List<LearnerProfileSkillViewDTO>> getAllLearners() {
        List<LearnerProfileSkillViewDTO> learners = profileSkillViewService.getLearners();
        return ResponseEntity.ok(learners);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherProfileSkillViewDTO>> getAllTeachers() {
        List<TeacherProfileSkillViewDTO> teachers = profileSkillViewService.getTeachers();
        return ResponseEntity.ok(teachers);
    }
}

