package com.clinicmanager.repository;

import com.clinicmanager.model.Patient;
import com.clinicmanager.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PatientRepository {

    public List<Patient> findAll() {

        EntityManager entityManager =
                JPAUtil.getEntityManager();

        try {

            return entityManager
                    .createQuery(
                            "SELECT p FROM Patient p",
                            Patient.class
                    )
                    .getResultList();

        } finally {

            entityManager.close();
        }
    }

    public void save(Patient patient) {

        EntityManager entityManager =
                JPAUtil.getEntityManager();

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(patient);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw e;

        } finally {

            entityManager.close();
        }
    }

    public Patient findById(Long id) {

        EntityManager entityManager =
                JPAUtil.getEntityManager();

        try {
            return entityManager.find(Patient.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void update(Patient patient) {

        EntityManager entityManager =
                JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.merge(patient);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw e;

        } finally {
            entityManager.close();
        }
    }
}