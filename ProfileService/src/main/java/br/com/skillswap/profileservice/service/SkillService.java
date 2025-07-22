package br.com.skillswap.profileservice.service;

import br.com.skillswap.profileservice.exception.ResourceNotFoundException;
import br.com.skillswap.profileservice.model.Skill;
import br.com.skillswap.profileservice.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> allSkill(){
        return skillRepository.findAll();
    }

    public Skill oneSkill(Long id) {
        if (skillRepository.findById(id).isPresent()) {
            return skillRepository.findById(id).get();
        }
        throw new ResourceNotFoundException("Não existe skill com esse id");
    }

    public Skill createSkill(Skill skill){
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
}
