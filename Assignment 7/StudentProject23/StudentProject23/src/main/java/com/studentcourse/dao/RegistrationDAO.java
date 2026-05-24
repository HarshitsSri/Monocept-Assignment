package com.studentcourse.dao;

import com.studentcourse.model.Registration;
import com.studentcourse.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    public int countRegistrations() {
        return countRows("registrations");
    }

    public List<Registration> findAll() {
        List<Registration> registrations = new ArrayList<>();

        String sql = "SELECT r.registration_id, r.student_id, r.course_id, r.registration_date, r.status, " +
                     "s.student_name, c.course_name " +
                     "FROM registrations r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "JOIN courses c ON r.course_id = c.course_id " +
                     "ORDER BY r.registration_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                registrations.add(mapRegistration(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching registrations", e);
        }

        return registrations;
    }

    public boolean existsActiveRegistration(int studentId, int courseId) {
        String sql = "SELECT 1 FROM registrations WHERE student_id = ? AND course_id = ? AND status = 'Active' LIMIT 1";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while checking duplicate active registration", e);
        }
    }

    public int create(Registration registration) {
        String sql = "INSERT INTO registrations(student_id, course_id, registration_date, status) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, registration.getStudentId());
            ps.setInt(2, registration.getCourseId());
            ps.setDate(3, registration.getRegistrationDate());
            ps.setString(4, registration.getStatus());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while creating registration", e);
        }

        return 0;
    }

    public boolean updateStatus(int registrationId, String status) {
        String sql = "UPDATE registrations SET status = ? WHERE registration_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, registrationId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error while updating registration status", e);
        }
    }

    public boolean delete(int registrationId) {
        String sql = "DELETE FROM registrations WHERE registration_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, registrationId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting registration", e);
        }
    }

    private Registration mapRegistration(ResultSet rs) throws SQLException {
        Registration registration = new Registration();
        registration.setRegistrationId(rs.getInt("registration_id"));
        registration.setStudentId(rs.getInt("student_id"));
        registration.setCourseId(rs.getInt("course_id"));
        registration.setStudentName(rs.getString("student_name"));
        registration.setCourseName(rs.getString("course_name"));
        registration.setRegistrationDate(rs.getDate("registration_date"));
        registration.setStatus(rs.getString("status"));
        return registration;
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
