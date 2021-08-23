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

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tableName) {
        Connection connection = getConnection();
        Statement statement = null;
        String sql;
        if(tableName.equals("developer")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `developer` \n" +
                        "(\n" +
                        "`iddeveloper` int(11) NOT NULL auto_increment,\n" +
                        "`fisrtname` varchar(11) NOT NULL,\n" +
                        "`lastname` varchar(11) NOT NULL,\n" +
                        "`idteam` int(11)\n" +
                        ");";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(tableName.equals("team")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `team` \n" +
                        "(\n" +
                        "`idteam` int(11) NOT NULL auto_increment,\n" +
                        "`name` varchar(11) NOT NULL,\n" +
                        "`status` varchar(11)\n" +
                        ");";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(tableName.equals("skill")) {
            try {
                statement = connection.createStatement();
                sql = "create table if not exists `skill` \n" +
                        "(\n" +
                        "`idskill` int(11) NOT NULL auto_increment,\n" +
                        "`name` varchar(11) NOT NULL,\n" +
                        ");";
                statement.execute(sql);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
