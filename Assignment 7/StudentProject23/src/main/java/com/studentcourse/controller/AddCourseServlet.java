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

@WebServlet("/course/add")
public class AddCourseServlet extends HttpServlet {

    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String courseName = request.getParameter("courseName");
        String duration = request.getParameter("duration");
        String feesStr = request.getParameter("fees");
        String trainerName = request.getParameter("trainerName");

        if (isBlank(courseName) || isBlank(duration) || isBlank(feesStr) || isBlank(trainerName)) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
                   .forward(request, response);
            return;
        }

        double fees;
        try {
            fees = Double.parseDouble(feesStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Fees must be a valid number.");
            request.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
                   .forward(request, response);
            return;
        }

        if (fees <= 0) {
            request.setAttribute("error", "Fees must be greater than 0.");
            request.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
                   .forward(request, response);
            return;
        }

        Course course = new Course();
        course.setCourseName(courseName.trim());
        course.setDuration(duration.trim());
        course.setFees(fees);
        course.setTrainerName(trainerName.trim());

        courseDAO.create(course);

        response.sendRedirect(request.getContextPath() + "/courses");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}