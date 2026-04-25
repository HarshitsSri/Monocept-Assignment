package com.monocept.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monocept.project.app.model.Student;

public class StudentDAO {
	// all done
	public boolean insertStudent(Connection connection, Student s) {

		String query = "INSERT INTO student(id, name, age, branch_id) " + "VALUES (?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, s.getId());
			preparedStatement.setString(2, s.getName());
			preparedStatement.setInt(3, s.getAge());
			preparedStatement.setInt(4, s.getBranchId());

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {

			System.out.println("Student insert SQL Error: " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Student insert Generic Error: " + e.getMessage());
		}

		return false;
	}

	// all done
	public boolean isStudentExist(Connection connection, int id) {

		String checkQuery = "select * from student where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultset = preparedStatement.executeQuery();) {
				return resultset.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Student exist SQLError: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Student exist Generic Error" + e.getMessage());
		}
		return false;
	}

	// all done
	public boolean deleteStudentRecord(Connection connection, int id) {

		String deleteQuery = "delete from student where id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {

			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// all done
	public boolean updateName(Connection connection, int id, String name) {
		String updateQuery = "update Student set name = ? where id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);) {

			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Set name error: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	// all done
	public boolean updateBranch(Connection connection, int id, int branchId) {

		String query = "UPDATE student " + "SET branch_id = ? " + "WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, branchId);
			preparedStatement.setInt(2, id);

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {

			System.out.println("Set branch error: " + e.getMessage());

			e.printStackTrace();
		}

		return false;
	}

}
