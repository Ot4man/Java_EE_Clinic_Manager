package com.clinicmanager.controller;

import com.clinicmanager.model.Patient;
import com.clinicmanager.repository.PatientRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/patients")
public class PatientListServlet extends HttpServlet {

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

        List<Patient> patients =
                patientRepository.findAll();

        request.setAttribute("patients", patients);

        request.getRequestDispatcher(
                "/admin/patients.jsp"
        ).forward(request, response);
    }
}