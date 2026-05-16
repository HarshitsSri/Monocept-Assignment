package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studentcourse.util.DBConnection;

public class AdminDAO {

	public boolean validateAdmin(String username, String password) {

		boolean status = false;

		try {

			Connection connection = DBConnection.getConnection();

			String query = "select * from `admin` where username=? and password=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, username);

			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				status = true;
			}

		} catch (Exception e) {

			e.getMessage();
		}

		return status;
	}
}