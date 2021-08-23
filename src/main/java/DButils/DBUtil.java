package DButils;

import java.sql.*;

public class DBUtil {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud_database";
    static final String USER = "maria";
    static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(DATABASE_URL,USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        return connection;
    }

    /*public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void createTable(String tableName) {
        Connection connection = getConnection();
        Statement statement = null;
        String sql;
        if(tableName.equals("developer")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `developer` (\n" +
                        "  `idDeveloper` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `developerFirstName` varchar(45) NOT NULL,\n" +
                        "  `developerLastName` varchar(45) NOT NULL,\n" +
                        "  `idTeam` int DEFAULT NULL,\n" +
                        "   primary key (`idDeveloper`)\n" +
                        ") ";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(tableName.equals("team")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `team` (\n" +
                        "  `idTeam` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `teamName` varchar(45) NOT NULL,\n" +
                        "  `status` varchar(45) NOT NULL,\n"+
                        "   primary key (`idTeam`)\n" +
                        ") ";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(tableName.equals("skill")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `skill` (\n" +
                        "  `idSkill` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `skillName` varchar(45) NOT NULL,\n" +
                        "   primary key (`idSkill`)\n" +
                        ") ";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(tableName.equals("skill_developer")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `skill_developer` (\n" +
                        "  `idSkill` int NOT NULL,\n" +
                        "  `idDeveloper` int NOT NULL,\n" +
                        "   primary key (`idSkill`)\n" +
                        ") ";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
