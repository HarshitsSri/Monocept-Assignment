package com.studentcourse.controller;

import java.io.IOException;
import java.util.List;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/courses")
public class ViewCoursesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("login");

			return;
		}

		CourseDAO dao = new CourseDAO();

		List<Course> courseList = dao.getAllCourses();

		request.setAttribute("courses", courseList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-list.jsp");

		rd.forward(request, response);
	}
}