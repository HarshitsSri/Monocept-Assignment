package com.studentcourse.controller;

import java.io.IOException;
import java.util.List;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registrations")
public class ViewRegistrationsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("login");

			return;
		}

		RegistrationDAO dao = new RegistrationDAO();

		List<Registration> list = dao.getAllRegistrations();

		request.setAttribute("registrations", list);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp");

		rd.forward(request, response);
	}
}