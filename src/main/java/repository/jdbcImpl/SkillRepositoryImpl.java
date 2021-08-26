package repository.jdbcImpl;

import DButils.DBUtil;
import model.Skill;
import repository.SkillRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {

    private static final Connection connection = DBUtil.getConnection();

    @Override
    public Skill save(Skill skill) {
        long id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into skill (skillName) values(?)"
                    , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,skill.getName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        skill.setId(id);
        return skill;
    }

    @Override
    public Skill getById(Long id) {
        String name = "";
        int idSkill = 0;
        String sql = "select * from skill where idSkill = " + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                idSkill = resultSet.getInt("idSkill");
                name = resultSet.getString("skillName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Skill((long) idSkill, name);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from skill where idSkill = " + id + ";";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        String sql = "select * from skill";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getInt("idSkill");
                String name = resultSet.getString("skillName");
                Skill skill = new Skill(id, name);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill update(Skill skill) {
        Skill skillNew = new Skill(
                skill.getId(),
                skill.getName());
        deleteById(skill.getId());
        save(skillNew);
        return skillNew;
    }

}
