package com.khush.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;
    private String qualification;
    private Integer experience;
    private String contactNumber;
    private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Doctor(Long id, String name, String specialization, String qualification, Integer experience,
			String contactNumber, String email) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.qualification = qualification;
		this.experience = experience;
		this.contactNumber = contactNumber;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", specialization=" + specialization + ", qualification="
				+ qualification + ", experience=" + experience + ", contactNumber=" + contactNumber + ", email=" + email
				+ "]";
	}
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}