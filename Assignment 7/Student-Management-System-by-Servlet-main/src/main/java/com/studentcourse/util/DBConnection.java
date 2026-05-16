package com.studentcourse.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String url = "jdbc:mysql://localhost:3306/student_course_db";

	private static final String username = "root";

	private static final String password = "12345678";

	public static Connection getConnection() {

		Connection connection = null;

		try {

			// Load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create Connection
			connection = DriverManager.getConnection(url, username, password);

			System.out.println("Connected Successfully");

		} catch (Exception e) {

			System.out.println("Connection Failed");

			e.printStackTrace();
		}

		return connection;
	}
}