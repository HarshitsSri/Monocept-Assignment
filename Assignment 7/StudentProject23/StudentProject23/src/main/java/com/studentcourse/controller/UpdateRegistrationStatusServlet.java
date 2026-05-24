package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.util.AuthUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration/status")
public class UpdateRegistrationStatusServlet extends HttpServlet {

    private final RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!AuthUtil.requireLogin(request, response)) {
            return;
        }

        String idStr = request.getParameter("registrationId");
        String status = request.getParameter("status");

        if (isBlank(idStr) || isBlank(status)) {
            request.getSession().setAttribute("flashError", "Registration ID and status are required.");
            response.sendRedirect(request.getContextPath() + "/registrations");
            return;
        }

        if (!("Active".equals(status) || "Completed".equals(status) || "Cancelled".equals(status))) {
            request.getSession().setAttribute("flashError", "Invalid registration status.");
            response.sendRedirect(request.getContextPath() + "/registrations");
            return;
        }

        try {
            int registrationId = Integer.parseInt(idStr);
            boolean updated = registrationDAO.updateStatus(registrationId, status);

            if (updated) {
                request.getSession().setAttribute("flashError", "Registration status updated successfully.");
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