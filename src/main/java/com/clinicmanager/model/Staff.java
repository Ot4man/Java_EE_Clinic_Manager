package com.clinicmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff extends User {

    public Staff() {
    }
}