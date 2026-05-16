package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

public class CourseDAO {

	// ADD COURSE
	public boolean addCourse(Course c) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "insert into courses(course_name,duration,fees,trainer_name) values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, c.getCourseName());

			ps.setString(2, c.getDuration());

			ps.setDouble(3, c.getFees());

			ps.setString(4, c.getTrainerName());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// VIEW ALL COURSES
	public List<Course> getAllCourses() {

		List<Course> list = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			String query = "select * from courses";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Course c = new Course();

				c.setCourseId(rs.getInt("course_id"));

				c.setCourseName(rs.getString("course_name"));

				c.setDuration(rs.getString("duration"));

				c.setFees(rs.getDouble("fees"));

				c.setTrainerName(rs.getString("trainer_name"));

				list.add(c);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// GET COURSE BY ID
	public Course getCourseById(int courseId) {

		Course c = null;

		try {

			Connection con = DBConnection.getConnection();

			String query = "select * from courses where course_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, courseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				c = new Course();

				c.setCourseId(rs.getInt("course_id"));

				c.setCourseName(rs.getString("course_name"));

				c.setDuration(rs.getString("duration"));

				c.setFees(rs.getDouble("fees"));

				c.setTrainerName(rs.getString("trainer_name"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return c;
	}

	// UPDATE COURSE
	public boolean updateCourse(Course c) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "update courses set course_name=?, duration=?, fees=?, trainer_name=? where course_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, c.getCourseName());

			ps.setString(2, c.getDuration());

			ps.setDouble(3, c.getFees());

			ps.setString(4, c.getTrainerName());

			ps.setInt(5, c.getCourseId());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// DELETE COURSE
	public boolean deleteCourse(int courseId) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "delete from courses where course_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, courseId);

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