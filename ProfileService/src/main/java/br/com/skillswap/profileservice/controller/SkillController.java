package br.com.skillswap.profileservice.controller;

import br.com.skillswap.profileservice.model.Skill;
import br.com.skillswap.profileservice.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> listAll(){
        return ResponseEntity.ok(skillService.allSkill());
    }

    @GetMapping
    public ResponseEntity<Skill> listById(@PathVariable Long id){
        return ResponseEntity.ok(skillService.oneSkill(id));
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.createSkill(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill (@PathVariable Long id, @RequestBody Skill skill) {
        skill.setSkillId(id);
        return ResponseEntity.ok(skillService.createSkill(skill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill (@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }



}
