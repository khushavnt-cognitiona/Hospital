package com.khush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khush.entity.Doctor;
import com.khush.exception.DoctorNotFoundException;
import com.khush.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@GetMapping("/get")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();

		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {

		Doctor doctor2 = doctorService.createDoctor(doctor);
		return new ResponseEntity<Doctor>(doctor2, HttpStatus.CREATED);

	}

	@GetMapping("/getby/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) throws DoctorNotFoundException {
		Doctor doctor = doctorService.getDoctorById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + id));

		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor)
			throws DoctorNotFoundException {

		Doctor doctor2 = doctorService.updateDoctor(id, doctor);

		return new ResponseEntity<Doctor>(doctor2, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) throws DoctorNotFoundException {

		doctorService.deleteDoctotr(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	

	
		
		
	
	}
	@GetMapping("/getBySpecialization/{specialization}")
	public ResponseEntity<List<Doctor>>getDoctorsBySpecialization(@PathVariable String specialization ){
		
		List<Doctor >doctors=doctorService.getDoctorsBySpecialization(specialization);
		
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
		
	}

}
