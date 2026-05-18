package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration/delete")
public class DeleteRegistrationServlet extends HttpServlet {

    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr = request.getParameter("id");

        if (isBlank(idStr)) {
            request.getSession().setAttribute("flashError", "Registration ID is missing.");
            response.sendRedirect(request.getContextPath() + "/registrations");
            return;
        }

        try {
            int registrationId = Integer.parseInt(idStr);
            boolean deleted = registrationDAO.delete(registrationId);

            if (deleted) {
                request.getSession().setAttribute("flashError", "Registration deleted successfully.");
            } else {
                request.getSession().setAttribute("flashError", "Registration not found.");
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "Invalid registration ID.");
        }

        response.sendRedirect(request.getContextPath() + "/registrations");
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}