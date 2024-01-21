package com.khush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khush.entity.Doctor;
import com.khush.exception.DoctorNotFoundException;
import com.khush.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public List<Doctor> getAllDoctors() {

		return doctorRepository.findAll();
	}

	public Doctor createDoctor(Doctor doctor) {

		return doctorRepository.save(doctor);
	}

	public Optional<Doctor> getDoctorById(Long id) throws DoctorNotFoundException {

		Optional<Doctor> optional = doctorRepository.findById(id);

		if (optional.isPresent()) {
			Doctor doctor = optional.get();
			return doctorRepository.findById(id);

		}

		else {

		}
		throw new DoctorNotFoundException("Doctor Not Found with this ID: " + id);

	}

	public Doctor updateDoctor(Long id, Doctor doctor) throws DoctorNotFoundException {

		Optional<Doctor> doctors = doctorRepository.findById(id);
		if (doctors.isPresent()) {
			Doctor doctor2 = doctors.get();

			doctor2.setContactNumber(doctor.getContactNumber());
			doctor2.setSpecialization(doctor.getSpecialization());
			doctor2.setQualification(doctor.getQualification());
			doctor2.setEmail(doctor.getEmail());
			doctor2.setContactNumber(doctor.getContactNumber());
			doctor2.setExperience(doctor.getExperience());
			doctor2.setName(doctor.getName());
			doctor.getId();
			return doctorRepository.save(doctor2);
		} else {

			throw new DoctorNotFoundException("Doctor not found with id:" + id);
		}

		
	}

	public void deleteDoctotr(Long id) throws DoctorNotFoundException {
	Optional<Doctor>optional=doctorRepository.findById(id);
	
	if(optional.isPresent()) {
		Doctor doctor=optional.get();
		
		doctorRepository.delete(doctor);
	}
	else {
	 throw new 	DoctorNotFoundException("Doctor not found with id "+id);
	}
		
	}

	public List<Doctor> getDoctorsBySpecialization(String specialization) {
		
		return doctorRepository.findBySpecialization(specialization);
	}
}
