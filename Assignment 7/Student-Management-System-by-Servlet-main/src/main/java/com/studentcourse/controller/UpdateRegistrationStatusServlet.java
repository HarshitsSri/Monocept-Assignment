package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.RegistrationDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registration/update")
public class UpdateRegistrationStatusServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int registrationId = Integer.parseInt(request.getParameter("id"));

		String status = request.getParameter("status");

		RegistrationDAO dao = new RegistrationDAO();

		dao.updateRegistrationStatus(registrationId, status);

		response.sendRedirect("../registrations");
	}
}