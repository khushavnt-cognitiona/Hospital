package com.khush.entity;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Date dob;
	
	private String gendar;
	private String address;
	private String phoneNumber;
	private String email;
	private String bloodtype;
	
	private String emergencyContact;
	
	private String emergencyphone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGendar() {
		return gendar;
	}

	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyphone() {
		return emergencyphone;
	}

	public void setEmergencyphone(String emergencyphone) {
		this.emergencyphone = emergencyphone;
	}

	public Patient(Long id, String firstName, String lastName, Date dob, String gendar, String address,
			String phoneNumber, String email, String bloodtype, String emergencyContact, String emergencyphone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gendar = gendar;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bloodtype = bloodtype;
		this.emergencyContact = emergencyContact;
		this.emergencyphone = emergencyphone;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gendar=" + gendar + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", bloodtype=" + bloodtype + ", emergencyContact=" + emergencyContact + ", emergencyphone="
				+ emergencyphone + "]";
	}
	

}
