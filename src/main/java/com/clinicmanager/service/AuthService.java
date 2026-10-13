package com.clinicmanager.service;

import com.clinicmanager.exception.DuplicateEmailException;
import com.clinicmanager.exception.UserNotFoundException;
import com.clinicmanager.model.Patient;
import com.clinicmanager.model.User;
import com.clinicmanager.repository.UserRepository;
import com.clinicmanager.util.PasswordUtil;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    public Patient register(
            String firstName,
            String lastName,
            String email,
            String phone,
            String password
    ) throws DuplicateEmailException {

        if (userRepository.findByEmail(email) != null) {
            throw new DuplicateEmailException(
                    "Cet email existe déjà."
            );
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException(
                    "Le mot de passe doit contenir au moins 6 caractères."
            );
        }

        Patient patient = new Patient();

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setEmail(email);
        patient.setPhone(phone);

        String hashedPassword =
                PasswordUtil.hashPassword(password);

        patient.setPassword(hashedPassword);

        userRepository.save(patient);

        return patient;
    }

    public User login(
            String email,
            String password
    ) throws UserNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(
                    "Email ou mot de passe incorrect."
            );
        }

        if (!user.isActive()) {
            throw new UserNotFoundException(
                    "Votre compte est désactivé."
            );
        }

        boolean passwordCorrect =
                PasswordUtil.checkPassword(
                        password,
                        user.getPassword()
                );

        if (!passwordCorrect) {
            throw new UserNotFoundException(
                    "Email ou mot de passe incorrect."
            );
        }

        return user;
    }
    public void changePassword(User user,String oldPassword,String newPassword) {
    boolean correct = PasswordUtil.checkPassword(oldPassword,user.getPassword());
    if(!correct) {
        throw new IllegalArgumentException("password incorrect");
    }
    if(newPassword == null || newPassword.length() < 6) {
        throw new IllegalArgumentException("new password should be 6 characters");
    }
    String hashedPassword = PasswordUtil.hashPassword(newPassword);
    user.setPassword(hashedPassword);
    userRepository.update(user);
    }
}