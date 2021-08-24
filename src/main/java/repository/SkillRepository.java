package repository;

import model.Skill;
import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long> {

    Skill save(Skill skill);

    Skill getById(Long id);

    void deleteById(Long id);

    List<Skill> getAll();

    Skill update(Skill skill);
}
