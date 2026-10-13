package com.clinicmanager.controller;

import com.clinicmanager.model.Patient;
import com.clinicmanager.repository.PatientRepository;
import com.clinicmanager.util.PasswordUtil;
import com.clinicmanager.exception.DuplicateEmailException;
import com.clinicmanager.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/patients/new")
public class PatientCreateServlet extends HttpServlet {

    private PatientRepository patientRepository;
    private UserRepository userRepository;
    @Override
    public void init() {

        patientRepository = new PatientRepository();
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.getRequestDispatcher(
                "/admin/patient-form.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Patient patient = new Patient();

        patient.setFirstName(
                request.getParameter("firstName")
        );

        patient.setLastName(
                request.getParameter("lastName")
        );

        String email = request.getParameter("email");

        if (userRepository.findByEmail(email) != null) {

            request.setAttribute(
                    "error",
                    "email already exist"
            );

            request.getRequestDispatcher(
                    "/admin/patient-form.jsp"
            ).forward(request, response);

            return;
        }
        patient.setEmail(
                request.getParameter("email")
        );

        patient.setPhone(
                request.getParameter("phone")
        );

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

        String password =
                request.getParameter("password");

        patient.setPassword(
                PasswordUtil.hashPassword(password)
        );

        patientRepository.save(patient);

        response.sendRedirect(
                request.getContextPath()
                        + "/admin/patients"
        );
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}