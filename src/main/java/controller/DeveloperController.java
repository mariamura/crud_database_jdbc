package controller;

import model.Developer;
import repository.DeveloperRepository;
import repository.jdbcImpl.DeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    DeveloperRepository developerRepository;

    public DeveloperController(DeveloperRepositoryImpl developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAll(){
        return developerRepository.getAll();
    }

    public Developer getById(Long id){
        return developerRepository.getById(id);
    }

    public Developer save(Developer developer){
        return developerRepository.save(developer);
    }

    public Developer update(Developer developer){
        return developerRepository.update(developer);
    }

    public void deleteById(Long id){
        developerRepository.deleteById(id);
    }

    public void printAll() {
        developerRepository.getAll().
                stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getFirstName() + " " + n.getLastName()));
    }
}
