package com.clinicmanager.controller;

import com.clinicmanager.model.Patient;
import com.clinicmanager.model.User;
import com.clinicmanager.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/patient/profile")
public class ProfileServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User sessionUser =
                (User) session.getAttribute("user");

        User user =
                userRepository.findById(sessionUser.getId());

        request.setAttribute("user", user);

        request.getRequestDispatcher(
                "/patient/profile.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        User sessionUser =
                (User) session.getAttribute("user");

        User user =
                userRepository.findById(sessionUser.getId());

        user.setFirstName(
                request.getParameter("firstName")
        );

        user.setLastName(
                request.getParameter("lastName")
        );

        user.setPhone(
                request.getParameter("phone")
        );

        if (user instanceof Patient) {

            Patient patient = (Patient) user;

            patient.setCin(
                    request.getParameter("cin")
            );

            String birthDate =
                    request.getParameter("birthDate");

            if (birthDate != null && !birthDate.isEmpty()) {
                patient.setBirthDate(
                        LocalDate.parse(birthDate)
                );
            }

            patient.setGender(
                    request.getParameter("gender")
            );

            patient.setAddress(
                    request.getParameter("address")
            );

            patient.setBloodType(
                    request.getParameter("bloodType")
            );
        }

        userRepository.update(user);

        session.setAttribute("user", user);

        response.sendRedirect(
                request.getContextPath()
                        + "/patient/profile"
        );
    }
}