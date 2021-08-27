package repository.jdbcImpl;

import DButils.DBUtil;
import model.Developer;
import model.Skill;
import model.Team;
import model.TeamStatus;
import repository.TeamRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {

    private static final Connection connection = DBUtil.getConnection();


    @Override
    public Team save(Team team) {
        long id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into team (teamName, teamStatus) values(?, ?)"
                    , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,team.getName());
            preparedStatement.setString(2,team.getTeamStatus().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }

            List<Developer> developers = team.getDevelopers();
            if(!developers.isEmpty()) {
                String sql;
                for(Developer developer: developers) {
                    sql = "update developer set idTeam = '" + id +  "' where iddeveloper = " + developer.getId();
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        team.setId(id);
        return team;
    }

    @Override
    public Team getById(Long id) {
        String name = "";
        int idTeam = 0;
        TeamStatus status = null;
        List<Developer> developers = new ArrayList<>();
        String sql = "select * from team where idTeam = " + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                idTeam = resultSet.getInt("idTeam");
                name = resultSet.getString("teamName");
                String statusString = resultSet.getString("teamStatus");
                if(statusString.equalsIgnoreCase("active")) status = TeamStatus.ACTIVE;
                else status = TeamStatus.DELETED;
            }

            sql = "select d.iddeveloper, d.developerFirstName, d.developerLastName\n" +
                    "from developer d\n" +
                    "join team as t on d.idTeam = t.idTeam\n" +
                    "where d.idTeam =" + idTeam  +";";
            resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                List<Skill> skillList = new ArrayList<>();
                int idDeveloper = resultSet.getInt("iddeveloper");
                String developerFirstName = resultSet.getString("developerFirstName");
                String developerLastName = resultSet.getString("developerLastName");

                sql = "select sk.idSkill, sk.skillName\n" +
                        "from developer d\n" +
                        "join skill_developer as s on d.iddeveloper = s.idDeveloper\n" +
                        "join skill as sk on s.idSkill = sk.idSkill\n" +
                        "where d.iddeveloper = " + idDeveloper + ";";
                resultSet = connection.createStatement().executeQuery(sql);
                while (resultSet.next()) {
                    int idSkill = resultSet.getInt("idSkill");
                    String skillName = resultSet.getString("skillName");
                    skillList.add(new Skill((long) idSkill, skillName));
                }

                developers.add(new Developer((long) idDeveloper, developerFirstName, developerLastName, skillList));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Team((long) idTeam, name, developers, status);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from team where idTeam = " + id + ";";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();
        String name = "";
        int idTeam = 0;
        TeamStatus status = null;
        String sql = "select * from team";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                idTeam = resultSet.getInt("idTeam");
                name = resultSet.getString("teamName");
                String statusString = resultSet.getString("teamStatus");
                if(statusString.equalsIgnoreCase("active")) status = TeamStatus.ACTIVE;
                else status = TeamStatus.DELETED;
                Team team = new Team((long) idTeam, name, status);
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public Team update(Team team) {
        Team teamNew = new Team(
                team.getId(),
                team.getName(),
                team.getTeamStatus());
        deleteById(team.getId());
        save(teamNew);
        return teamNew;
    }

}
