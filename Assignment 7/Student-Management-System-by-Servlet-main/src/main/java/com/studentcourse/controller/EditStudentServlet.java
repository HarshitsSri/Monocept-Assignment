package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {

	@Override
	public void init() {

		System.out.println("EditStudentServlet Initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("../login");

			return;
		}

		int studentId = Integer.parseInt(request.getParameter("id"));

		StudentDAO dao = new StudentDAO();

		Student student = dao.getStudentById(studentId);

		request.setAttribute("student", student);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");

		rd.forward(request, response);
	}

	@Override
	public void destroy() {

		System.out.println("EditStudentServlet Destroyed");
	}
}