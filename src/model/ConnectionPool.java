package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
	static Connection con;

	String url = "jdbc:oracle:thin:@61.81.99.87:1521:bigdata";
	String user = "cash";
	String pass = "vote";
	String driver = "oracle.jdbc.driver.OracleDriver";

	private ConnectionPool() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public static Connection getConnection() throws Exception {
		if (con == null) {
			ConnectionPool cp = new ConnectionPool();
		}
		return con;
	}

}
