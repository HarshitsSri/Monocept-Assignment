package com.studentcourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.model.Registration;
import com.studentcourse.util.DBConnection;

public class RegistrationDAO {

	// ADD REGISTRATION
	public boolean addRegistration(Registration r) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "insert into registrations(student_id,course_id,registration_date,status) values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, r.getStudentId());

			ps.setInt(2, r.getCourseId());

			ps.setString(3, r.getRegistrationDate());

			ps.setString(4, r.getStatus());

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// VIEW ALL REGISTRATIONS
	public List<Registration> getAllRegistrations() {

		List<Registration> list = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			String query = "select * from registrations";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Registration r = new Registration();

				r.setRegistrationId(rs.getInt("registration_id"));

				r.setStudentId(rs.getInt("student_id"));

				r.setCourseId(rs.getInt("course_id"));

				r.setRegistrationDate(rs.getString("registration_date"));

				r.setStatus(rs.getString("status"));

				list.add(r);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// UPDATE STATUS
	public boolean updateRegistrationStatus(int registrationId, String statusValue) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "update registrations set status=? where registration_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, statusValue);

			ps.setInt(2, registrationId);

			int rows = ps.executeUpdate();

			if (rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// DELETE REGISTRATION
	public boolean deleteRegistration(int registrationId) {

		boolean status = false;

		try {

			Connection con = DBConnection.getConnection();

			String query = "delete from registrations where registration_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, registrationId);

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