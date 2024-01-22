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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(Date appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Appointment(Long id, Date appointmentDateTime, Patient patient, Doctor doctor, String appointmentType,
			String location, String notes) {
		super();
		this.id = id;
		this.appointmentDateTime = appointmentDateTime;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
		this.location = location;
		this.notes = notes;
	}
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Constructors, getters, and setters
    
    
}
