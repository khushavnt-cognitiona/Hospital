package com.khush.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHander {
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<String>PatientNotFoundExceptionHander(PatientNotFoundException notFoundException){
		
		
		return new ResponseEntity<>(notFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<String>DoctorNotFoundExceptionHandeler(DoctorNotFoundException doctorNotFoundException){
		
		
		return new ResponseEntity<String>(doctorNotFoundException.getLocalizedMessage(),HttpStatus.NOT_FOUND);
		
	}
}
