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

import com.khush.entity.Appointment;
import com.khush.entity.Doctor;
import com.khush.exception.DoctorNotFoundException;
import com.khush.exception.TimeSlotNotAvailableException;
import com.khush.service.AppointmentsService;
import com.khush.service.DoctorService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	AppointmentsService appointmentsService;
	@Autowired
	DoctorService doctorService;

	@GetMapping("/get")
	public ResponseEntity<List<Appointment>> getAllAppointments() {

		List<Appointment> appointments = appointmentsService.getAllAppointments();

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);

	}

	@PostMapping("/post")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment)
			throws TimeSlotNotAvailableException {
		Appointment appointment2 = appointmentsService.createAppointment(appointment);

		return new ResponseEntity<Appointment>(appointment2, HttpStatus.CREATED);

	}

	@GetMapping("get/{id}")
	public ResponseEntity<Appointment> getAppointmentsById(@PathVariable Long id) {

		Appointment appointment = appointmentsService.getAppointmentsById(id);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
		appointmentsService.cancelAppointment(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PutMapping("update/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		Appointment appointment2 = appointmentsService.updateAppointment(id, appointment);
		return new ResponseEntity<Appointment>(appointment2, HttpStatus.OK);

	}

	@GetMapping("/getByDoctor/{doctorId}")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId)
			throws DoctorNotFoundException {
	    Doctor doctor = doctorService.getDoctorById(doctorId)
	            .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with ID: " + doctorId));

	    List<Appointment> appointments = appointmentsService.getAppointmentsByDoctor(doctor);
	    return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

}