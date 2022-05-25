package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLConnector {
	public ResultSet getDBConnectionAndExecuteQuery(String queryToExecute) throws SQLException {
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://database-1.csxwfz3yi9pw.ap-south-1.rds.amazonaws.com:5432/pidge-1", "postgres",
					"bycdsXmws9wHDUirqdVX");
			Statement statement = conn.createStatement();

			rs = statement.executeQuery(queryToExecute);
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			conn.close();
			System.exit(0);
		}
		return rs;
	}
}
