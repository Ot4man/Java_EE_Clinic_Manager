package com.clinicmanager.controller;

import com.clinicmanager.exception.DuplicateEmailException;
import com.clinicmanager.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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

        String firstName =
                request.getParameter("firstName");

        String lastName =
                request.getParameter("lastName");

        String email =
                request.getParameter("email");

        String phone =
                request.getParameter("phone");

        String password =
                request.getParameter("password");

        String confirmPassword =
                request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Les mots de passe ne correspondent pas."
            );

            request.getRequestDispatcher(
                    "/auth/register.jsp"
            ).forward(request, response);

            return;
        }

        try {

            authService.register(
                    firstName,
                    lastName,
                    email,
                    phone,
                    password
            );

            response.sendRedirect(
                    request.getContextPath() + "/auth/login.jsp"
            );

        } catch (DuplicateEmailException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/auth/register.jsp"
            ).forward(request, response);

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher(
                    "/auth/register.jsp"
            ).forward(request, response);
        }
    }
}