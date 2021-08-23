package repository.jdbcImpl;

import DButils.DBUtil;
import model.Developer;
import repository.DeveloperRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {

    private static Connection connection = DBUtil.getConnection();

    static {
        DBUtil.createTable("developer");
        DBUtil.createTable("skill");
        DBUtil.createTable("skill_developer");
    }

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
        int iddeveloper = 0;
        String sql = "select * from developer where iddeveloper = " + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                iddeveloper = resultSet.getInt("iddeveloper");
                firstname = resultSet.getString("developerFirstName");
                lastname = resultSet.getString("developerLastName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Developer((long) iddeveloper, firstname, lastname);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from developer where iddeveloper = " + id + ";";
        try {
            connection.createStatement().executeQuery(sql);
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
                long id = resultSet.getInt("iddeveloper");
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
        return null;
    }

    @Override
    public Long getLastId() {
        return null;
    }
}
