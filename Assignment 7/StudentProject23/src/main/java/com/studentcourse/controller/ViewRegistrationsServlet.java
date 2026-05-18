package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrations")
public class ViewRegistrationsServlet extends HttpServlet {

    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        request.setAttribute("registrations", registrationDAO.findAll());

        request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp")
               .forward(request, response);
    }
}