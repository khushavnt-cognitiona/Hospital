package com.khush.entity;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDateTime;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    private String appointmentType; // e.g., follow-up, consultation, etc.
    private String location; // e.g., clinic room, virtual, etc.
    private String notes; // Additional notes or comments for the appointment

    // Constructors, getters, and setters
    
    
}
