package controller;

import model.Team;
import repository.TeamRepository;
import repository.jdbcImpl.TeamRepositoryImpl;

import java.util.List;

public class TeamController {

    TeamRepositoryImpl teamRepository;

    public TeamController(TeamRepositoryImpl teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAll(){
        return teamRepository.getAll();
    }

    public Team getById(Long id){
        return teamRepository.getById(id);
    }

    public Team save(Team team){
        return teamRepository.save(team);
    }

    public Team update(Team team){
        return teamRepository.update(team);
    }

    public void deleteById(Long id){
        teamRepository.deleteById(id);
    }

    public void printAll() {
        teamRepository.getAll().stream().
                forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
    }
}
