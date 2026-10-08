package com.clinicmanager.controller;

import com.clinicmanager.exception.UserNotFoundException;
import com.clinicmanager.model.User;
import com.clinicmanager.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            User user = authService.login(email, password);

            HttpSession session = request.getSession();

            session.setAttribute("user", user);

            response.sendRedirect(
                    request.getContextPath() + "/patient/dashboard.jsp"
            );

        } catch (UserNotFoundException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/auth/login.jsp"
            ).forward(request, response);
        }
    }
}