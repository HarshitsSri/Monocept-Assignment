package com.studentcourse.controller;

import java.io.IOException;
import java.util.List;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/students")
public class ViewStudentsServlet extends HttpServlet {

	@Override
	public void init() {

		System.out.println("ViewStudentsServlet Initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedInUser") == null) {

			response.sendRedirect("login");

			return;
		}

		StudentDAO dao = new StudentDAO();

		List<Student> studentList = dao.getAllStudents();

		request.setAttribute("students", studentList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/student-list.jsp");

		rd.forward(request, response);
	}

	@Override
	public void destroy() {

		System.out.println("ViewStudentsServlet Destroyed");
	}
}