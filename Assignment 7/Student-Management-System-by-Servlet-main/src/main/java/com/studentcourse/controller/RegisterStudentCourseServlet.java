package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registration/save")
public class RegisterStudentCourseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int studentId = Integer.parseInt(request.getParameter("studentId"));

		int courseId = Integer.parseInt(request.getParameter("courseId"));

		String registrationDate = request.getParameter("registrationDate");

		String status = request.getParameter("status");

		Registration r = new Registration();

		r.setStudentId(studentId);

		r.setCourseId(courseId);

		r.setRegistrationDate(registrationDate);

		r.setStatus(status);

		RegistrationDAO dao = new RegistrationDAO();

		boolean result = dao.addRegistration(r);

		if (result) {

			response.sendRedirect("../registrations");

		} else {

			request.setAttribute("error", "Registration Failed");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");

			rd.forward(request, response);
		}
	}
}