package controller;

import model.Team;
import repository.TeamRepository;
import repository.jdbcImpl.TeamRepositoryImpl;

import java.util.List;

public class TeamController {
    TeamRepository teamRepository = new TeamRepositoryImpl();

    public List<Team> getAll(){
        return teamRepository.getAll();
    }

    public Team getById(Long id){
        return teamRepository.getById(id);
    }

    public void save(Team team){
        teamRepository.save(team);
    }

    public void update(Team team){
        teamRepository.update(team);
    }

    public void deleteById(Long id){
        teamRepository.deleteById(id);
    }

    public void printAll() {
        teamRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }
}
