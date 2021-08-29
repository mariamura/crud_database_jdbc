package controller;

import model.Skill;
import repository.jdbcImpl.SkillRepositoryImpl;

import java.util.List;

public class SkillController {

    SkillRepositoryImpl skillRepository;

    public SkillController(SkillRepositoryImpl skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAll(){
        return skillRepository.getAll();
    }

    public Skill getById(Long id){
        return skillRepository.getById(id);
    }

    public Skill save(Skill skill){
        return skillRepository.save(skill);
    }

    public Skill update(Skill skill){
        return skillRepository.update(skill);
    }

    public void deleteById(Long id){
        skillRepository.deleteById(id);
    }

    public void printAll() {
        skillRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }
}
