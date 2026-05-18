package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private StudentDAO studentDAO = new StudentDAO();

	private CourseDAO courseDAO = new CourseDAO();

	private RegistrationDAO registrationDAO = new RegistrationDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect(request.getContextPath() + "/login");

			return;
		}

		request.setAttribute("totalStudents", studentDAO.countStudents());

		request.setAttribute("totalCourses", courseDAO.countCourses());

		request.setAttribute("totalRegistrations", registrationDAO.countRegistrations());

		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
}
