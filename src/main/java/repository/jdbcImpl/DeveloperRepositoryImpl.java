package repository.jdbcImpl;

import DButils.DBUtil;
import model.Developer;
import model.Skill;
import repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {

    private static final Connection connection = DBUtil.getConnection();

    @Override
    public Developer save(Developer developer) {
        long id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into developer (developerFirstName, developerLastName) values(?, ?)"
                    , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,developer.getFirstName());
            preparedStatement.setString(2,developer.getLastName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            List<Skill> skills = developer.getSkills();

            if(!skills.isEmpty()) {
                PreparedStatement preparedStatement2 = connection.prepareStatement(
                        "insert into skill_developer (idSkill, idDeveloper) values(?," + id + ")");
                ResultSet resultSet;
                String sql;

                for(Skill skill: skills) {
                    sql = "select * from skill where skillName = '" + skill.getName() + "'";
                    resultSet = connection.createStatement().executeQuery(sql);
                    resultSet.next();
                    int idSkill = resultSet.getInt("idSkill");
                    preparedStatement2.setString(1, String.valueOf(idSkill));
                    preparedStatement2.executeUpdate();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        developer.setId(id);
        return developer;
    }

    @Override
    public Developer getById(Long id) {
        String firstname = "";
        String lastname = "";
        String skillName;
        int idDeveloper = 0;
        int idSkill = 0;
        List<Skill> skillList = new ArrayList<>();
        String sql;
        try {
            sql = "select * from developer where idDeveloper = " + id + ";";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                idDeveloper = resultSet.getInt("idDeveloper");
                firstname = resultSet.getString("developerFirstName");
                lastname = resultSet.getString("developerLastName");
            }
            sql = "select sk.idSkill, sk.skillName\n" +
                    "from developer d\n" +
                    "join skill_developer as s on d.iddeveloper = s.idDeveloper\n" +
                    "join skill as sk on s.idSkill = sk.idSkill\n" +
                    "where d.iddeveloper = " + idDeveloper + ";";
            resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                idSkill = resultSet.getInt("idSkill");
                skillName = resultSet.getString("skillName");
                skillList.add(new Skill((long) idSkill, skillName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Developer((long) idDeveloper, firstname, lastname, skillList);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from developer where idDeveloper = " + id + ";";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        String sql = "select * from developer";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getInt("idDeveloper");
                String firstname = resultSet.getString("developerFirstName");
                String lastname = resultSet.getString("developerLastName");
                Developer developer = new Developer(id, firstname, lastname);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public Developer update(Developer developer) {
        Developer developerNew = new Developer(
                developer.getId(),
                developer.getFirstName(),
                developer.getLastName(),
                developer.getSkills());
        deleteById(developer.getId());
        save(developerNew);
        return developerNew;
    }
}
