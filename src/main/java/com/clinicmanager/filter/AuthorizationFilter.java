package com.clinicmanager.filter;

import com.clinicmanager.model.Admin;
import com.clinicmanager.model.Doctor;
import com.clinicmanager.model.Patient;
import com.clinicmanager.model.Staff;
import com.clinicmanager.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/patient/*", "/doctor/*", "/admin/*", "/staff/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        if (session == null) {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/auth/login.jsp"
            );
            return;
        }

        User user =
                (User) session.getAttribute("user");

        if (user == null) {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath()
                            + "/auth/login.jsp"
            );
            return;
        }

        String uri = httpRequest.getRequestURI();

        if (uri.contains("/patient/") && user instanceof Patient) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.contains("/doctor/") && user instanceof Doctor) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.contains("/admin/") && user instanceof Admin) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.contains("/staff/") && user instanceof Staff) {
            chain.doFilter(request, response);
            return;
        }

        httpResponse.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                "Access denied"
        );
    }
}