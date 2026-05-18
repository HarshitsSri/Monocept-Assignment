package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

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

            boolean deleted = studentDAO.deleteIfNotRegistered(studentId);

            if (deleted) {
                request.getSession().setAttribute("flashError", "Student deleted successfully.");
            } else {
                request.getSession().setAttribute("flashError", "Student cannot be deleted because registrations exist.");
            }

            response.sendRedirect(request.getContextPath() + "/students");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "Invalid student ID.");
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}