package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/course/edit")
public class EditCourseServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isBlank()) {
            request.getSession().setAttribute("flashError", "Course ID is missing.");
            response.sendRedirect(request.getContextPath() + "/courses");
            return;
        }

        try {
            int courseId = Integer.parseInt(idStr);
            Course course = courseDAO.findById(courseId);

            if (course == null) {
                request.getSession().setAttribute("flashError", "Course not found.");
                response.sendRedirect(request.getContextPath() + "/courses");
                return;
            }

            request.setAttribute("course", course);
            request.getRequestDispatcher("/WEB-INF/views/course-edit.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "Invalid course ID.");
            response.sendRedirect(request.getContextPath() + "/courses");
        }
    }
}