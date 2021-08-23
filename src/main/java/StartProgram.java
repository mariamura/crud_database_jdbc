import DButils.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class StartProgram {
	public static void main(String[] args) throws Exception {
		Connection connection =  DBUtil.getConnection();
		Statement statement = connection.createStatement();
		String sql = "select * from team";
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("idteam");
			String name = resultSet.getString("teamName");
			String status = resultSet.getString("teamStatus");

			System.out.println(id + ": " + name + ", " + status);
		}

		resultSet.close();
		statement.close();
		connection.close();

	}
}
