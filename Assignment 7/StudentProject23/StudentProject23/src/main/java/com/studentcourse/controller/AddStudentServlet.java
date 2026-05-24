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
import java.util.LinkedHashMap;
import java.util.Map;

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

        String name = trim(request.getParameter("studentName"));
        String email = trim(request.getParameter("email"));
        String phone = trim(request.getParameter("phone"));
        String ageStr = trim(request.getParameter("age"));
        String city = trim(request.getParameter("city"));

        Map<String, String> errors = new LinkedHashMap<>();

        validateName(name, errors);
        validateEmail(email, errors);
        validatePhone(phone, errors);
        Integer age = validateAge(ageStr, errors);
        validateCity(city, errors);

        if (errors.isEmpty()) {
            if (studentDAO.existsByEmail(email)) {
                errors.put("email", "Email already exists.");
            }

            if (studentDAO.existsByPhone(phone)) {
                errors.put("phone", "Phone number already exists.");
            }
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("studentName", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("age", ageStr);
            request.setAttribute("city", city);

            request.getRequestDispatcher("/WEB-INF/views/student-form.jsp")
                   .forward(request, response);
            return;
        }

        Student student = new Student();
        student.setStudentName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAge(age);
        student.setCity(city);

        studentDAO.create(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void validateName(String name, Map<String, String> errors) {
        if (isBlank(name)) {
            errors.put("studentName", "Student name is required.");
            return;
        }

        if (name.length() < 2 || name.length() > 100) {
            errors.put("studentName", "Student name must be between 2 and 100 characters.");
            return;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            errors.put("studentName", "Student name should contain only letters and spaces.");
        }
    }

    private void validateEmail(String email, Map<String, String> errors) {
        if (isBlank(email)) {
            errors.put("email", "Email is required.");
            return;
        }

        if (email.length() > 100) {
            errors.put("email", "Email must not exceed 100 characters.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.put("email", "Enter a valid email address.");
        }
    }

    private void validatePhone(String phone, Map<String, String> errors) {
        if (isBlank(phone)) {
            errors.put("phone", "Phone number is required.");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            errors.put("phone", "Phone number must be exactly 10 digits.");
        }
    }

    private Integer validateAge(String ageStr, Map<String, String> errors) {
        if (isBlank(ageStr)) {
            errors.put("age", "Age is required.");
            return null;
        }

        try {
            int age = Integer.parseInt(ageStr);
            if (age < 18) {
                errors.put("age", "Age must be 18 or above.");
            } else if (age > 100) {
                errors.put("age", "Age looks invalid.");
            }
            return age;
        } catch (NumberFormatException e) {
            errors.put("age", "Age must be a valid number.");
            return null;
        }
    }

    private void validateCity(String city, Map<String, String> errors) {
        if (isBlank(city)) {
            errors.put("city", "City is required.");
            return;
        }

        if (city.length() < 2 || city.length() > 50) {
            errors.put("city", "City must be between 2 and 50 characters.");
            return;
        }

        if (!city.matches("[a-zA-Z\\s.']+")) {
            errors.put("city", "City should contain only letters and spaces.");
        }
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}