package repository;

import model.Developer;
import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    Developer save(Developer developer);

    Developer getById(Long id);

    void deleteById(Long id);

    List<Developer> getAll();

    Developer update(Developer developer);

}
