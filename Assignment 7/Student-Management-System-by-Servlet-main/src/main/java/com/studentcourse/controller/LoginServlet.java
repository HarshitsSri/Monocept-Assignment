package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.AdminDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void init() {
		System.out.println("LoginServlet Initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");

		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username").trim();

		String password = request.getParameter("password").trim();

		String remember = request.getParameter("remember");

		AdminDAO dao = new AdminDAO();

		boolean status = dao.validateAdmin(username, password);
		System.out.println(username);
		System.out.println(password);
		System.out.println(status);

		if (status) {

			HttpSession session = request.getSession();

			session.setAttribute("loggedInUser", username);

			if (remember != null) {

				Cookie cookie = new Cookie("username", username);

				cookie.setMaxAge(60 * 60 * 24);

				response.addCookie(cookie);
			}

			response.sendRedirect("dashboard");

		} else {

			request.setAttribute("error", "Invalid Username or Password");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");

			rd.forward(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("LoginServlet Destroyed");
	}
}