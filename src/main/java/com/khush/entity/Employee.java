package com.khush.entity;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String employeeType; // Doctor, Nurse, Administrator, etc.
    // Add more employee details (contact information, qualifications, etc.)

    // Relationships
    @OneToMany(mappedBy = "employee")
    private List<MedicalRecord> medicalRecords;

    // Constructors, getters, setters, etc.
    
}
