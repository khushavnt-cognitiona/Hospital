package com.khush.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Patient patient;

	private Date date; // Date of the medical record entry
	private String diagnosis;
	private String treatment;
	private String medications;
	private String allergies;
	private String notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public MedicalRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecord(Long id, Patient patient, Date date, String diagnosis, String treatment, String medications,
			String allergies, String notes) {
		super();
		this.id = id;
		this.patient = patient;
		this.date = date;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.medications = medications;
		this.allergies = allergies;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "MedicalRecord [id=" + id + ", patient=" + patient + ", date=" + date + ", diagnosis=" + diagnosis
				+ ", treatment=" + treatment + ", medications=" + medications + ", allergies=" + allergies + ", notes="
				+ notes + "]";
	}

	// Constructors, getters, and setters

}
