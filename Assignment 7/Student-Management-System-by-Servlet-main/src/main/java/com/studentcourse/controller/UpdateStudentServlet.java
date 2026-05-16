package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/update")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    public void init() {

        System.out.println("UpdateStudentServlet Initialized");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null) {

            response.sendRedirect("../login");

            return;
        }

        int studentId =
                Integer.parseInt(
                        request.getParameter("studentId")
                );

        String studentName =
                request.getParameter("studentName");

        String email =
                request.getParameter("email");

        String phone =
                request.getParameter("phone");

        int age =
                Integer.parseInt(
                        request.getParameter("age")
                );

        String city =
                request.getParameter("city");

        // VALIDATION
        if(age < 18) {

            request.setAttribute(
                    "error",
                    "Age must be 18 or above"
            );

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/WEB-INF/views/student-edit.jsp"
                    );

            rd.forward(request, response);

            return;
        }

        Student s =
                new Student();

        s.setStudentId(studentId);

        s.setStudentName(studentName);

        s.setEmail(email);

        s.setPhone(phone);

        s.setAge(age);

        s.setCity(city);

        StudentDAO dao =
                new StudentDAO();

        boolean status =
                dao.updateStudent(s);

        if(status) {

            response.sendRedirect(
                    "../students"
            );

        } else {

            request.setAttribute(
                    "error",
                    "Student Update Failed"
            );

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/WEB-INF/views/student-edit.jsp"
                    );

            rd.forward(request, response);
        }
    }

    @Override
    public void destroy() {

        System.out.println("UpdateStudentServlet Destroyed");
    }
}