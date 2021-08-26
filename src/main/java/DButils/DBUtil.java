package DButils;

import java.sql.*;

public class DBUtil {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/crud_database?serverTimezone=Europe/Moscow";
    private static final String USER = "maria";
    private static final String PASSWORD = "123";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
