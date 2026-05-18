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

@WebServlet("/student/add")
public class AddStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/student-form.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String name = request.getParameter("studentName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String ageStr = request.getParameter("age");
        String city = request.getParameter("city");

        if (isBlank(name) || isBlank(email) || isBlank(phone) || isBlank(ageStr) || isBlank(city)) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/views/student-form.jsp")
                   .forward(request, response);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Age must be a valid number.");
            request.getRequestDispatcher("/WEB-INF/views/student-form.jsp")
                   .forward(request, response);
            return;
        }

        if (age < 18) {
            request.setAttribute("error", "Age must be 18 or above.");
            request.getRequestDispatcher("/WEB-INF/views/student-form.jsp")
                   .forward(request, response);
            return;
        }

        Student student = new Student();
        student.setStudentName(name.trim());
        student.setEmail(email.trim());
        student.setPhone(phone.trim());
        student.setAge(age);
        student.setCity(city.trim());

        studentDAO.create(student);

        response.sendRedirect(request.getContextPath() + "/students");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}