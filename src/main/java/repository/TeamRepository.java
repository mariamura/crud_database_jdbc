package repository;

import model.Team;
import java.util.List;

public interface TeamRepository extends GenericRepository<Team, Long> {

    Team save(Team team);

    Team getById(Long id);

    void deleteById(Long id);

    List<Team> getAll();

    Team update(Team team);
}
