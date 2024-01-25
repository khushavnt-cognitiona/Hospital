package com.khush.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khush.entity.Appointment;
import com.khush.entity.Doctor;
import com.khush.exception.AppointmentNotFoundException;
import com.khush.exception.TimeSlotNotAvailableException;
import com.khush.repository.AppointmentsRepository;

@Service
public class AppointmentsService {

	@Autowired
	AppointmentsRepository appointmentsRepository;

	public List<Appointment> getAllAppointments() {

		return appointmentsRepository.findAll();
	}

	public Appointment createAppointment(Appointment appointment) throws TimeSlotNotAvailableException {

		if (!isAppointmentTimeSlotAvailable(appointment.getAppointmentDateTime(), appointment.getDoctor())) {
			throw new TimeSlotNotAvailableException(
					"The appointment time slot is not available. Please choose a different time.");

		}

		return appointmentsRepository.save(appointment);
	}

	private boolean isAppointmentTimeSlotAvailable(Date appointmentDateTime, Doctor doctor) {

		Optional<List<Appointment>> optional = appointmentsRepository.findByDoctorAndAppointmentDateTime(doctor,
				appointmentDateTime);

		return optional.map(List::isEmpty).orElse(true);

	}

	public Appointment getAppointmentsById(Long id) {
		Optional<Appointment> appointments = appointmentsRepository.findById(id);

		if (appointments.isPresent()) {
			Appointment appointment = appointments.get();
			return appointment;
		} else {
			throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
		}

	}

	public void cancelAppointment(Long id) {
		Optional<Appointment>optional=appointmentsRepository.findById(id);
		
		if(optional.isPresent()) {
			Appointment appointment=optional.get();
			
			appointmentsRepository.delete(appointment);
		}else {
			throw new AppointmentNotFoundException("Appointment not found with ID");
		}
		
	}

	public Appointment updateAppointment(Long id, Appointment appointment) {
	Optional<Appointment>optionalAppointment=appointmentsRepository.findById(id);
	if(optionalAppointment.isPresent()) {
		
		
		Appointment existingAppointment = optionalAppointment.get();

        // Update fields from the provided appointment
        existingAppointment.setAppointmentDateTime(appointment.getAppointmentDateTime());
        existingAppointment.setPatient(appointment.getPatient());
        existingAppointment.setDoctor(appointment.getDoctor());
        existingAppointment.setAppointmentType(appointment.getAppointmentType());
        existingAppointment.setLocation(appointment.getLocation());
        existingAppointment.setNotes(appointment.getNotes());
        
        return appointmentsRepository.save(existingAppointment);

		
	}else {
		throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
	}
	
	}

	

	public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
		
		return appointmentsRepository.findByDoctor(doctor);
	}

}
