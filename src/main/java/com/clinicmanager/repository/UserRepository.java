package com.clinicmanager.repository;

import com.clinicmanager.model.User;
import com.clinicmanager.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserRepository {

    public User findByEmail(String email) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            return entityManager
                    .createQuery(
                            "SELECT u FROM User u WHERE u.email = :email",
                            User.class
                    )
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;

        } finally {
            entityManager.close();
        }
    }

    public void save(User user) {

        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user);

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