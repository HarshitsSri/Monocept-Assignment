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

@WebServlet("/student/update")
public class UpdateStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr = request.getParameter("studentId");
        String name = request.getParameter("studentName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String ageStr = request.getParameter("age");
        String city = request.getParameter("city");

        int studentId = 0;
        Student student = new Student();

        try {
            if (idStr != null && !idStr.isBlank()) {
                studentId = Integer.parseInt(idStr);
                student.setStudentId(studentId);
            }
        } catch (NumberFormatException ignored) {
        }

        student.setStudentName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setCity(city);

        try {
            if (ageStr != null && !ageStr.isBlank()) {
                student.setAge(Integer.parseInt(ageStr));
            }
        } catch (NumberFormatException ignored) {
        }

        if (isBlank(idStr) || isBlank(name) || isBlank(email) || isBlank(phone) || isBlank(ageStr) || isBlank(city)) {
            request.setAttribute("error", "All fields are required.");
            request.setAttribute("student", studentDAO.findById(studentId));
            request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp").forward(request, response);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid numeric value.");
            request.setAttribute("student", studentDAO.findById(studentId));
            request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp").forward(request, response);
            return;
        }

        if (age < 18) {
            request.setAttribute("error", "Age must be 18 or above.");
            request.setAttribute("student", studentDAO.findById(studentId));
            request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp").forward(request, response);
            return;
        }

        student.setStudentId(studentId);
        student.setStudentName(name.trim());
        student.setEmail(email.trim());
        student.setPhone(phone.trim());
        student.setAge(age);
        student.setCity(city.trim());

        studentDAO.update(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}