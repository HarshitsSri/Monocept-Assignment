package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/course/add")
public class AddCourseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("../login");

			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-form.jsp");

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseName = request.getParameter("courseName");

		String duration = request.getParameter("duration");

		double fees = Double.parseDouble(request.getParameter("fees"));

		String trainerName = request.getParameter("trainerName");

		Course c = new Course();

		c.setCourseName(courseName);

		c.setDuration(duration);

		c.setFees(fees);

		c.setTrainerName(trainerName);

		CourseDAO dao = new CourseDAO();

		boolean status = dao.addCourse(c);

		if (status) {

			response.sendRedirect("../courses");

		} else {

			request.setAttribute("error", "Course Add Failed");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/course-form.jsp");

			rd.forward(request, response);
		}
	}
}