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

@WebServlet("/course/update")
public class UpdateCourseServlet extends HttpServlet {

    private final CourseDAO courseDAO =
            new CourseDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr =
                request.getParameter("courseId");

        String courseName =
                request.getParameter("courseName");

        String duration =
                request.getParameter("duration");

        String feesStr =
                request.getParameter("fees");

        String trainerName =
                request.getParameter("trainerName");

        int courseId = 0;

        Course course = new Course();

        try {

            if (idStr != null &&
                    !idStr.isBlank()) {

                courseId =
                        Integer.parseInt(idStr);

                course.setCourseId(courseId);
            }

        } catch (NumberFormatException ignored) {
        }

        course.setCourseName(courseName);
        course.setDuration(duration);
        course.setTrainerName(trainerName);

        try {

            if (feesStr != null &&
                    !feesStr.isBlank()) {

                course.setFees(
                        Double.parseDouble(feesStr));
            }

        } catch (NumberFormatException ignored) {
        }

        if (isBlank(idStr) ||
                isBlank(courseName) ||
                isBlank(duration) ||
                isBlank(feesStr) ||
                isBlank(trainerName)) {

            request.setAttribute(
                    "error",
                    "All fields are required.");

            request.setAttribute(
                    "course",
                    course);

            request.getRequestDispatcher(
                    "/WEB-INF/views/course-edit.jsp")
                    .forward(request, response);

            return;
        }

        double fees;

        try {

            fees =
                    Double.parseDouble(feesStr);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Fees must be a valid number.");

            request.setAttribute(
                    "course",
                    course);

            request.getRequestDispatcher(
                    "/WEB-INF/views/course-edit.jsp")
                    .forward(request, response);

            return;
        }

        if (fees <= 0) {

            request.setAttribute(
                    "error",
                    "Fees must be greater than 0.");

            request.setAttribute(
                    "course",
                    course);

            request.getRequestDispatcher(
                    "/WEB-INF/views/course-edit.jsp")
                    .forward(request, response);

            return;
        }

        course.setCourseId(courseId);
        course.setCourseName(courseName.trim());
        course.setDuration(duration.trim());
        course.setFees(fees);
        course.setTrainerName(trainerName.trim());

        courseDAO.update(course);

        response.sendRedirect(
                request.getContextPath()
                        + "/courses");
    }

    private boolean isBlank(String value) {

        return value == null ||
                value.isBlank();
    }
}