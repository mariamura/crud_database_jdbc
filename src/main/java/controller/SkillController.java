package controller;

import model.Skill;
import repository.SkillRepository;
import repository.jdbcImpl.SkillRepositoryImpl;

import java.util.List;

public class SkillController {
    SkillRepository skillRepository = new SkillRepositoryImpl();

    public List<Skill> getAll(){
        return skillRepository.getAll();
    }

    public Skill getById(Long id){
        return skillRepository.getById(id);
    }

    public void save(Skill skill){
        skillRepository.save(skill);
    }

    public void update(Skill skill){
        skillRepository.update(skill);
    }

    public void deleteById(Long id){
        skillRepository.deleteById(id);
    }

    public void printAll() {
        skillRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }
}
