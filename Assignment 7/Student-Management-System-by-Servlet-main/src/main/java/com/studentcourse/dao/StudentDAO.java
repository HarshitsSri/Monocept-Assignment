package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

public class StudentDAO {

	// ADD STUDENT
	public boolean addStudent(Student s) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "insert into students(student_name,email,phone,age,city) values(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, s.getStudentName());

			ps.setString(2, s.getEmail());

			ps.setString(3, s.getPhone());

			ps.setInt(4, s.getAge());

			ps.setString(5, s.getCity());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// VIEW ALL STUDENTS
	public List<Student> getAllStudents() {

		List<Student> list = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			String query = "select * from students";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student s = new Student();

				s.setStudentId(rs.getInt("student_id"));

				s.setStudentName(rs.getString("student_name"));

				s.setEmail(rs.getString("email"));

				s.setPhone(rs.getString("phone"));

				s.setAge(rs.getInt("age"));

				s.setCity(rs.getString("city"));

				list.add(s);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// GET STUDENT BY ID
	public Student getStudentById(int studentId) {

		Student s = null;

		try {

			Connection con = DBConnection.getConnection();

			String query = "select * from students where student_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				s = new Student();

				s.setStudentId(rs.getInt("student_id"));

				s.setStudentName(rs.getString("student_name"));

				s.setEmail(rs.getString("email"));

				s.setPhone(rs.getString("phone"));

				s.setAge(rs.getInt("age"));

				s.setCity(rs.getString("city"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return s;
	}

	// UPDATE STUDENT
	public boolean updateStudent(Student s) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "update students set student_name=?, email=?, phone=?, age=?, city=? where student_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, s.getStudentName());

			ps.setString(2, s.getEmail());

			ps.setString(3, s.getPhone());

			ps.setInt(4, s.getAge());

			ps.setString(5, s.getCity());

			ps.setInt(6, s.getStudentId());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// DELETE STUDENT
	public boolean deleteStudent(int studentId) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "delete from students where student_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, studentId);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}
}