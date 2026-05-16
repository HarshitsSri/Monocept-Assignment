package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

	@Override
	public void init() {

		System.out.println("DeleteStudentServlet Initialized");
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

		boolean status = dao.deleteStudent(studentId);

		if (status) {

			response.sendRedirect("../students");

		} else {

			response.sendRedirect("../students");
		}
	}

	@Override
	public void destroy() {

		System.out.println("DeleteStudentServlet Destroyed");
	}
}