package com.khush.entity;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class MedicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Test name is required")
    private String testName;

    private Date testDate;

    private String testResult;

    private String testDescription;

    private String prescribedMedication;

    private String testingFacility;

    // New field: price
    private Double price;

    // Many-to-One relationship with Patient entity
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Many-to-One relationship with Doctor entity
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

	public MedicalTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalTest(Long id, @NotBlank(message = "Test name is required") String testName, Date testDate,
			String testResult, String testDescription, String prescribedMedication, String testingFacility,
			Double price, Patient patient, Doctor doctor) {
		super();
		this.id = id;
		this.testName = testName;
		this.testDate = testDate;
		this.testResult = testResult;
		this.testDescription = testDescription;
		this.prescribedMedication = prescribedMedication;
		this.testingFacility = testingFacility;
		this.price = price;
		this.patient = patient;
		this.doctor = doctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getPrescribedMedication() {
		return prescribedMedication;
	}

	public void setPrescribedMedication(String prescribedMedication) {
		this.prescribedMedication = prescribedMedication;
	}

	public String getTestingFacility() {
		return testingFacility;
	}

	public void setTestingFacility(String testingFacility) {
		this.testingFacility = testingFacility;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "MedicalTest [id=" + id + ", testName=" + testName + ", testDate=" + testDate + ", testResult="
				+ testResult + ", testDescription=" + testDescription + ", prescribedMedication=" + prescribedMedication
				+ ", testingFacility=" + testingFacility + ", price=" + price + ", patient=" + patient + ", doctor="
				+ doctor + "]";
	}

    // Constructors, getters, setters, and other necessary methods

    // Additional methods as needed
    
}
