package com.monocept.project.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public Connection connectionToDatabase() {

		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/jdbcassignment2";
		String user = "root";
		String password = "12345678";

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection error: " + e.getMessage());
			// e.printStackTrace();
		}
		return connection;

	}

}
