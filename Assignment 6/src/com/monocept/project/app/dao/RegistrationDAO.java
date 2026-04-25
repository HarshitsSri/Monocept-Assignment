package com.monocept.project.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monocept.project.app.model.Registration;

public class RegistrationDAO {
	// final done
	public boolean addCourse(Connection connection, Registration r) throws SQLException {

		String query = "INSERT INTO registration(student_id, course_id, fees_paid) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, r.getStudentId());
			preparedStatement.setInt(2, r.getCourseId());
			preparedStatement.setDouble(3, r.getFeesPaid());

			return preparedStatement.executeUpdate() > 0;
		}
	}

	public boolean isCourseAlreadyRegistered(int student_id, int course_id, Connection connection) {

		String query = "SELECT reg_id FROM registration WHERE student_id = ? AND course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, student_id);
			preparedStatement.setInt(2, course_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void displayStudentWithCourses(Connection connection) {

		String query = "SELECT s.id, s.name, s.age, " + "b.branch_name, " + "c.course_name, " + "r.fees_paid "
				+ "FROM student s " + "JOIN branch b ON s.branch_id = b.branch_id "
				+ "LEFT JOIN registration r ON s.id = r.student_id "
				+ "LEFT JOIN course c ON r.course_id = c.course_id " + "ORDER BY s.id";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			int currentId = -1;
			boolean dataFound = false;

			while (resultSet.next()) {

				int id = resultSet.getInt("id");

				if (id != currentId) {

					System.out.println("\n----------------------");
					System.out.println("ID: " + id);
					System.out.println("Name: " + resultSet.getString("name"));
					System.out.println("Age: " + resultSet.getInt("age"));
					System.out.println("Branch: " + resultSet.getString("branch_name"));
					System.out.println("Courses:");

					currentId = id;
					dataFound = true;
				}

				String course = resultSet.getString("course_name");

				if (course != null) {
					System.out.println("  - " + course + " | Fee: " + resultSet.getDouble("fees_paid"));
				} else {
					System.out.println("  No courses registered");
				}
			}

			if (!dataFound) {
				System.out.println("No students found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateCourseFee(Connection connection, int student_id, int course_id, double updated_fees_paid) {
		String query = "UPDATE registration " + "SET fees_paid = ? " + "WHERE student_id = ? AND course_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setDouble(1, updated_fees_paid);
			preparedStatement.setInt(2, student_id);
			preparedStatement.setInt(3, course_id);

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean cancelCourseRegistration(Connection connection, int studentId, int courseId) {

		String query = "DELETE FROM registration " + "WHERE student_id = ? AND course_id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// all done
	public boolean deleteAllRegistrationOfAStudent(Connection connection, int studentId) {
		String deleteRegistrationQuery = "delete from registration where student_id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteRegistrationQuery)) {

			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void courseWiseCount(Connection connection) {
		String query = "SELECT c.course_name, COUNT(*) AS student_count " + "FROM registration r "
				+ "JOIN course c ON r.course_id = c.course_id " + "GROUP BY c.course_name";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			boolean found = false;
			while (resultSet.next()) {

				System.out.println("Course: " + resultSet.getString("course_name"));
				System.out.println("Students: " + resultSet.getInt("student_count"));
				System.out.println("----------------------");

				found = true;
			}
			if (!found) {
				System.out.println("No course data found");
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching course-wise count: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// all done
	public void displayDetailsById(Connection connection, int student_id) {

		String query = "SELECT s.id, s.name, s.age, " + "b.branch_name, " + "c.course_name, " + "r.fees_paid "
				+ "FROM student s " + "JOIN branch b ON s.branch_id = b.branch_id "
				+ "LEFT JOIN registration r ON s.id = r.student_id "
				+ "LEFT JOIN course c ON r.course_id = c.course_id " + "WHERE s.id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, student_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				boolean studentFound = false;
				while (resultSet.next()) {
					if (!studentFound) {
						System.out.println("\nStudent Details:");
						System.out.println("ID: " + resultSet.getInt("id"));
						System.out.println("Name: " + resultSet.getString("name"));
						System.out.println("Age: " + resultSet.getInt("age"));
						System.out.println("Branch: " + resultSet.getString("branch_name"));
						System.out.println("Courses:");
						studentFound = true;
					}

					String course = resultSet.getString("course_name");

					if (course != null) {

						System.out.println("  - " + course + " | Fee: " + resultSet.getDouble("fees_paid"));

					} else {

						System.out.println("  No courses registered");
					}
				}

				if (!studentFound) {
					System.out.println("Student not found");
				}
			}

		} catch (SQLException e) {
			System.out.println("Error fetching student data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// all done
	public void getHighPayingStudentsTotal(Connection connection, double amount) {

		if (amount <= 0) {
			System.out.println("Enter positive amount");
			return;
		}
		String query = "SELECT s.id, s.name, b.branch_name, " + "SUM(r.fees_paid) AS total_fee " + "FROM student s "
				+ "JOIN branch b ON s.branch_id = b.branch_id " + "JOIN registration r ON s.id = r.student_id "
				+ "GROUP BY s.id, s.name, b.branch_name " + "HAVING total_fee > ? " + "ORDER BY total_fee DESC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDouble(1, amount);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				boolean studentFound = false;
				while (resultSet.next()) {

					System.out.println("\n----------------------");
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Name: " + resultSet.getString("name"));
					System.out.println("Branch: " + resultSet.getString("branch_name"));
					System.out.println("Total Fee Paid: " + resultSet.getDouble("total_fee"));
					studentFound = true;
				}

				if (!studentFound) {
					System.out.println("No high paying students found");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
