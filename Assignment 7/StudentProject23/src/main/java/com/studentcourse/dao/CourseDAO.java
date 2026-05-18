package com.studentcourse.dao;

import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public int countCourses() {
        return countRows("courses");
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                courses.add(mapCourse(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching courses", e);
        }

        return courses;
    }

    public Course findById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCourse(rs);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching course by id", e);
        }

        return null;
    }

    public int create(Course course) {
        String sql = "INSERT INTO courses(course_name, duration, fees, trainer_name) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDuration());
            ps.setDouble(3, course.getFees());
            ps.setString(4, course.getTrainerName());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while creating course", e);
        }

        return 0;
    }

    public boolean update(Course course) {
        String sql = "UPDATE courses SET course_name = ?, duration = ?, fees = ?, trainer_name = ? WHERE course_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDuration());
            ps.setDouble(3, course.getFees());
            ps.setString(4, course.getTrainerName());
            ps.setInt(5, course.getCourseId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error while updating course", e);
        }
    }

    public boolean deleteIfNoActiveRegistration(int courseId) {
        if (hasActiveRegistration(courseId)) {
            return false;
        }

        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting course", e);
        }
    }

    public boolean hasActiveRegistration(int courseId) {
        String sql = "SELECT 1 FROM registrations WHERE course_id = ? AND status = 'Active' LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while checking active registration", e);
        }
    }

    private Course mapCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseName(rs.getString("course_name"));
        course.setDuration(rs.getString("duration"));
        course.setFees(rs.getDouble("fees"));
        course.setTrainerName(rs.getString("trainer_name"));
        return course;
    }

    private int countRows(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while counting records", e);
        }

        return 0;
    }
}