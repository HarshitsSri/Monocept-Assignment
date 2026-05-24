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
import java.util.LinkedHashMap;
import java.util.Map;

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

        String courseName = trim(request.getParameter("courseName"));
        String duration = trim(request.getParameter("duration"));
        String feesStr = trim(request.getParameter("fees"));
        String trainerName = trim(request.getParameter("trainerName"));

        Map<String, String> errors = new LinkedHashMap<>();

        if (isBlank(courseName)) {
            errors.put("courseName", "Course name is required.");
        } else if (courseName.length() < 2 || courseName.length() > 100) {
            errors.put("courseName", "Course name must be between 2 and 100 characters.");
        }

        if (isBlank(duration)) {
            errors.put("duration", "Duration is required.");
        } else if (duration.length() < 2 || duration.length() > 50) {
            errors.put("duration", "Duration must be between 2 and 50 characters.");
        }

        Double fees = null;
        if (isBlank(feesStr)) {
            errors.put("fees", "Fees is required.");
        } else {
            try {
                fees = Double.parseDouble(feesStr);
                if (fees <= 0) {
                    errors.put("fees", "Fees must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                errors.put("fees", "Fees must be a valid number.");
            }
        }

        if (isBlank(trainerName)) {
            errors.put("trainerName", "Trainer name is required.");
        } else if (trainerName.length() < 2 || trainerName.length() > 100) {
            errors.put("trainerName", "Trainer name must be between 2 and 100 characters.");
        }

        if (!errors.isEmpty()) {
            Course course = new Course();
            course.setCourseName(courseName);
            course.setDuration(duration);
            course.setTrainerName(trainerName);
            if (fees != null) {
                course.setFees(fees);
            }

            request.setAttribute("errors", errors);
            request.setAttribute("course", course);
            request.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
                   .forward(request, response);
            return;
        }

        Course course = new Course();
        course.setCourseName(courseName);
        course.setDuration(duration);
        course.setFees(fees);
        course.setTrainerName(trainerName);

        courseDAO.create(course);
        response.sendRedirect(request.getContextPath() + "/courses");
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}