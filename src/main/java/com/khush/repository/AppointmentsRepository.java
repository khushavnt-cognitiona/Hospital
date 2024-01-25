package com.khush.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khush.entity.Appointment;
import com.khush.entity.Doctor;
@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

	Optional<List<Appointment>> findByDoctorAndAppointmentDateTime(Doctor doctor, Date appointmentDateTime);

	
	List<Appointment> findByDoctor(Doctor doctor);

}
