
package com.clinicmanager.controller;

import com.clinicmanager.model.Patient;
import com.clinicmanager.repository.PatientRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/patients/edit")
public class PatientEditServlet extends HttpServlet {

    private PatientRepository patientRepository;

    @Override
    public void init() {
        patientRepository = new PatientRepository();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

        Patient patient = patientRepository.findById(id);

        if (patient == null) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Patient not found"
            );
            return;
        }

        request.setAttribute("patient", patient);

        request.getRequestDispatcher(
                "/admin/patient-edit.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

        Patient patient = patientRepository.findById(id);

        if (patient == null) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Patient not found"
            );
            return;
        }

        patient.setFirstName(
                request.getParameter("firstName")
        );

        patient.setLastName(
                request.getParameter("lastName")
        );

        patient.setPhone(
                request.getParameter("phone")
        );

        patient.setCin(
                request.getParameter("cin")
        );

        String birthDate = request.getParameter("birthDate");

        if (birthDate != null && !birthDate.isEmpty()) {
            patient.setBirthDate(LocalDate.parse(birthDate));
        } else {
            patient.setBirthDate(null);
        }

        patient.setGender(request.getParameter("gender"));
        patient.setAddress(request.getParameter("address"));
        patient.setBloodType(request.getParameter("bloodType"));

        patientRepository.update(patient);

        response.sendRedirect(
                request.getContextPath() + "/admin/patients"
        );
    }
}