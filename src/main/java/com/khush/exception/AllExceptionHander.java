package com.khush.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHander {

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String> PatientNotFoundExceptionHander(PatientNotFoundException notFoundException) {

		return new ResponseEntity<>(notFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String> DoctorNotFoundExceptionHandeler(DoctorNotFoundException doctorNotFoundException) {

		return new ResponseEntity<String>(doctorNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(TimeSlotNotAvailableException.class)
	public ResponseEntity<String> TimeSlotNotAvailableExceptionHandlar(
			TimeSlotNotAvailableException notAvailableException) {
		return new ResponseEntity<String>(notAvailableException.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<String> AppointmentNotFoundExceptionHandlar(
			AppointmentNotFoundException appointmentNotFoundException) {

		return new ResponseEntity<String>(appointmentNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MedicalRecordNotFoundException.class)
	public ResponseEntity<String> MedicalRecordNotFoundExceptionHandlar(
			MedicalRecordNotFoundException medicalRecordNotFoundException) {
		return new ResponseEntity<String>(medicalRecordNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);

	}
}
