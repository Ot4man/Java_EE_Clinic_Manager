package com.clinicmanager.controller;

import com.clinicmanager.model.User;
import com.clinicmanager.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/patient/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher(
                "/patient/change-password.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String oldPassword =
                request.getParameter("oldPassword");

        String newPassword =
                request.getParameter("newPassword");

        String confirmPassword =
                request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Les nouveaux mots de passe ne correspondent pas."
            );

            request.getRequestDispatcher(
                    "/patient/change-password.jsp"
            ).forward(request, response);

            return;
        }

        HttpSession session =
                request.getSession(false);

        User user =
                (User) session.getAttribute("user");

        try {

            authService.changePassword(
                    user,
                    oldPassword,
                    newPassword
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/patient/profile"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/patient/change-password.jsp"
            ).forward(request, response);
        }
    }
}