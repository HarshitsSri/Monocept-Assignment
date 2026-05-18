package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isBlank()) {
            request.getSession().setAttribute("flashError", "Student ID is missing.");
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        try {
            int studentId = Integer.parseInt(idStr);
            Student student = studentDAO.findById(studentId);

            if (student == null) {
                request.getSession().setAttribute("flashError", "Student not found.");
                response.sendRedirect(request.getContextPath() + "/students");
                return;
            }

            request.setAttribute("student", student);
            request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "Invalid student ID.");
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}