package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/course/delete")
public class DeleteCourseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("../login");

			return;
		}

		int courseId = Integer.parseInt(request.getParameter("id"));

		CourseDAO dao = new CourseDAO();

		boolean status = dao.deleteCourse(courseId);

		if (status) {

			response.sendRedirect("../courses");

		} else {

			response.sendRedirect("../courses");
		}
	}
}