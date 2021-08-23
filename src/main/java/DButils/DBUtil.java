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
                sql = "CREATE TABLE IF NOT EXISTS `developer`.`crud_database` (`col` VARCHAR(16) NOT NULL )";
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
