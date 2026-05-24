package com.studentcourse.controller;

import com.studentcourse.dao.AdminDAO;
import com.studentcourse.model.Admin;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login-action")
public class LoginServlet extends HttpServlet {

	private AdminDAO adminDAO = new AdminDAO();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		username = (username != null) ? username.trim() : null;
		password = (password != null) ? password.trim() : null;

		if (username == null || username.isBlank() || password == null || password.isBlank()) {
		    request.setAttribute("error", "Username and Password are required");
		    request.setAttribute("username", username);
		    request.setAttribute("rememberChecked", "on".equals(remember));
		    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		    return;
		}
		Admin admin = adminDAO.validateLogin(username, password);
		if (admin != null) {

			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", admin.getUsername());

			// COOKIE CODE START
			if ("on".equals(remember)) {
				Cookie cookie = new Cookie("rememberedUsername", admin.getUsername());
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
			} else {
				AuthUtil.clearCookie(response, "rememberedUsername");
			}
			// COOKIE CODE END
			response.sendRedirect(request.getContextPath() + "/dashboard");

		} else {
			request.setAttribute("error", "Invalid Username or Password");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}