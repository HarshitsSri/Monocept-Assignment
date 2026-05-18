package com.studentcourse.controller;

import com.studentcourse.util.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie cookie = AuthUtil.findCookie(request, "rememberedUsername");

        if (cookie != null && cookie.getValue() != null && !cookie.getValue().isBlank()) {
            request.setAttribute("rememberedUsername", cookie.getValue());
        }

        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }
}