package com.monocept.project.app.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.monocept.project.app.dao.RegistrationDAO;
import com.monocept.project.app.dao.StudentDAO;

import com.monocept.project.app.model.Registration;
import com.monocept.project.app.model.Student;
import com.monocept.project.app.util.DBUtil;

public class StudentService {

	DBUtil dbutil = new DBUtil();
	StudentDAO studentDao = new StudentDAO();
	RegistrationDAO registrationDao = new RegistrationDAO();

	// method 1
	public void studentRegistrationService(Registration r) {

		Connection connection = null;

		try {

			connection = dbutil.connectionToDatabase();
			connection.setAutoCommit(false);

			int studentId = r.getStudentId();
			int courseId = r.getCourseId();
			double feesPaid = r.getFeesPaid();

			if (feesPaid <= 0) {
				System.out.println("Fee must be greater than 0");
				return;
			}

			if (!studentDao.isStudentExist(connection, studentId)) {
				System.out.println("Invalid student ID");
				return;
			}

			if (registrationDao.isCourseAlreadyRegistered(studentId, courseId, connection)) {

				System.out.println("Student already registered for this course");

				return;
			}

			boolean inserted = registrationDao.addCourse(connection, r);

			if (!inserted) {

				System.out.println("Registration failed");

				connection.rollback();
				return;
			}

			connection.commit();

			System.out.println("Registration completed successfully");

		} catch (Exception e) {

			try {

				if (connection != null) {
					connection.rollback();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			System.out.println("Error occurred during registration");

			e.printStackTrace();

		} finally {

			try {

				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// method 2
	public void addNewStudent(Student s) {

		Connection connection = null;

		try {

			connection = dbutil.connectionToDatabase();

			int id = s.getId();
			int age = s.getAge();
			int branchId = s.getBranchId();
			String name = s.getName();

			if (name == null || name.trim().isEmpty() || name.trim().length() < 2) {

				System.out.println("Invalid name");

				return;
			}

			if (age <= 0) {

				System.out.println("Age must be greater than 0");

				return;
			}

			if (branchId < 1 || branchId > 5) {

				System.out.println("Invalid branch selection");

				return;
			}

			if (studentDao.isStudentExist(connection, id)) {

				System.out.println("Student already exists with this ID");

				return;
			}

			boolean inserted = studentDao.insertStudent(connection, s);

			if (!inserted) {

				System.out.println("Student insertion failed");

				return;
			}

			System.out.println("Student data stored successfully");

		} catch (Exception e) {

			System.out.println("Student insertion service layer error: " + e.getMessage());

			e.printStackTrace();

		} finally {

			try {

				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {

				System.out.println("Connection close error");

				e.printStackTrace();
			}
		}
	}

	// method 3
	public void viewAllStudentWithRegistration() {
		Connection connection = null;
		try {
			connection = dbutil.connectionToDatabase();
			registrationDao.displayStudentWithCourses(connection);

		} catch (Exception e) {
			System.out.println("View all student with registration error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// method 4
	public void viewAllDetailsOfStudentById(int id) {
		Connection connection = null;
		try {
			connection = dbutil.connectionToDatabase();
			registrationDao.displayDetailsById(connection, id);
		} catch (Exception e) {
			System.out.println("viewdetailsbyid error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// method 5
	public void updateStudentDetails(int id, String name, int branchId, int choice) {

		Connection connection = null;

		try {

			connection = dbutil.connectionToDatabase();


			if (!studentDao.isStudentExist(connection, id)) {

				System.out.println("No student exists with this ID");

				return;
			}


			if (choice == 1) {

				if (name == null || name.trim().length() < 2) {

					System.out.println("Invalid name");

					return;
				}

				if (studentDao.updateName(connection, id, name)) {

					System.out.println("Name changed successfully");

					return;
				}

				System.out.println("Name change failed");

				return;
			}

			else if (choice == 2) {

				if (branchId < 1 || branchId > 5) {

					System.out.println("Invalid branch selection");

					return;
				}

				if (studentDao.updateBranch(connection, id, branchId)) {

					System.out.println("Branch changed successfully");

					return;
				}

				System.out.println("Branch change failed");

				return;
			}

			System.out.println("Invalid choice");

		} catch (Exception e) {

			System.out.println("Student update service layer error: " + e.getMessage());

			e.printStackTrace();

		} finally {

			if (connection != null) {

				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// method 6
	public void updateCourseFees(int studentId, int courseId, double newFeesPaid) {

		Connection connection = null;

		try {

			connection = dbutil.connectionToDatabase();


			if (newFeesPaid <= 0) {

				System.out.println("Fees must be greater than 0");

				return;
			}


			if (!studentDao.isStudentExist(connection, studentId)) {

				System.out.println("Invalid student ID");

				return;
			}


			if (!registrationDao.isCourseAlreadyRegistered(studentId, courseId, connection)) {

				System.out.println("Student is not registered in this course");

				return;
			}


			boolean updated = registrationDao.updateCourseFee(connection, studentId, courseId, newFeesPaid);

			if (updated) {

				System.out.println("Course fee updated successfully");

				return;
			}

			System.out.println("Course fee update failed");

		} catch (Exception e) {

			System.out.println("Update course fee error: " + e.getMessage());

			e.printStackTrace();

		} finally {

			if (connection != null) {

				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// method 7
	public void cancelRegistration(int studentId, int courseId) {

		Connection connection = null;

		try {

			connection = dbutil.connectionToDatabase();


			if (!studentDao.isStudentExist(connection, studentId)) {

				System.out.println("Invalid student ID");

				return;
			}


			if (!registrationDao.isCourseAlreadyRegistered(studentId, courseId, connection)) {

				System.out.println("Student is not registered in this course");

				return;
			}


			boolean deleted = registrationDao.cancelCourseRegistration(connection, studentId, courseId);

			if (deleted) {

				System.out.println("Registration cancelled successfully");

				return;
			}

			System.out.println("Failed to cancel registration");

		} catch (Exception e) {

			System.out.println("Cancel registration error: " + e.getMessage());

			e.printStackTrace();

		} finally {

			if (connection != null) {

				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

		public void deleteStudentCompletely(int id) {

	    Connection connection = null;

	    try {

	        connection = dbutil.connectionToDatabase();

	        connection.setAutoCommit(false);

	        
	        if (!studentDao.isStudentExist(connection, id)) {

	            System.out.println(
	                    "No student exists with this ID");

	            return;
	        }

	        
	        registrationDao
	                .deleteAllRegistrationOfAStudent(
	                        connection,
	                        id);

	        System.out.println(
	                "All registrations deleted");

	        
	        boolean deleted =
	                studentDao.deleteStudentRecord(
	                        connection,
	                        id);

	        if (!deleted) {

	            System.out.println(
	                    "Student deletion failed");

	            connection.rollback();

	            return;
	        }

	       
	        connection.commit();

	        System.out.println(
	                "Student deleted successfully");

	    } catch (Exception e) {

	        System.out.println(
	                "Delete student error: "
	                        + e.getMessage());

	        try {

	            if (connection != null) {
	                connection.rollback();
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        e.printStackTrace();

	    } finally {

	        if (connection != null) {

	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	// method 9
	public void highPayingStudents(double amount) {

		Connection connection = null;
		try {
			connection = dbutil.connectionToDatabase();
			registrationDao.getHighPayingStudentsTotal(connection, amount);
		} catch (Exception e) {
			System.out.println("High paying report error: " + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// method 10
	public void courseWiseCountService() {
		Connection connection = null;
		try {
			connection = dbutil.connectionToDatabase();
			registrationDao.courseWiseCount(connection);
		} catch (Exception e) {
			System.out.println("Course-wise report error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
