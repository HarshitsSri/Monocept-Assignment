package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Registration;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/registration/submit")
public class RegisterStudentCourseServlet extends HttpServlet {

    private final RegistrationDAO registrationDAO = new RegistrationDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String studentIdStr = request.getParameter("studentId");
        String courseIdStr = request.getParameter("courseId");
        String registrationDateStr = request.getParameter("registrationDate");
        String status = request.getParameter("status");

        if (isBlank(studentIdStr) || isBlank(courseIdStr) || isBlank(registrationDateStr) || isBlank(status)) {
            request.setAttribute("error", "All fields are required.");
            loadFormData(request);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp")
                   .forward(request, response);
            return;
        }

        int studentId;
        int courseId;

        try {
            studentId = Integer.parseInt(studentIdStr);
            courseId = Integer.parseInt(courseIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid student or course selection.");
            loadFormData(request);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp")
                   .forward(request, response);
            return;
        }

        if (!("Active".equals(status) || "Completed".equals(status) || "Cancelled".equals(status))) {
            request.setAttribute("error", "Invalid status.");
            loadFormData(request);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp")
                   .forward(request, response);
            return;
        }

        try {
            Date regDate = Date.valueOf(registrationDateStr);

            if ("Active".equals(status) && registrationDAO.existsActiveRegistration(studentId, courseId)) {
                request.setAttribute("error", "Duplicate active registration is not allowed.");
                loadFormData(request);
                request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp")
                       .forward(request, response);
                return;
            }

            Registration registration = new Registration();
            registration.setStudentId(studentId);
            registration.setCourseId(courseId);
            registration.setRegistrationDate(regDate);
            registration.setStatus(status);

            registrationDAO.create(registration);
            response.sendRedirect(request.getContextPath() + "/registrations");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid registration date.");
            loadFormData(request);
            request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp")
                   .forward(request, response);
        }
    }

    private void loadFormData(HttpServletRequest request) {
        request.setAttribute("students", studentDAO.findAll());
        request.setAttribute("courses", courseDAO.findAll());
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}