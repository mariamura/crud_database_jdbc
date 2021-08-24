package controller;

import model.Developer;
import repository.DeveloperRepository;
import repository.jdbcImpl.DeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    DeveloperRepository developerRepository = new DeveloperRepositoryImpl();

    public List<Developer> getAll(){
        return developerRepository.getAll();
    }

    public Developer getById(Long id){
        return developerRepository.getById(id);
    }

    public void save(Developer developer){
        developerRepository.save(developer);
    }

    public void update(Developer developer){
        developerRepository.update(developer);
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
