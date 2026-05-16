package com.studentcourse.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	@Override
	public void init() {

		System.out.println("DashboardServlet Initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("login");

			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");

		rd.forward(request, response);
	}

	@Override
	public void destroy() {

		System.out.println("DashboardServlet Destroyed");
	}
}