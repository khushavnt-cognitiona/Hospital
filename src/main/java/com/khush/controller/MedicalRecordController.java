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

import com.khush.entity.MedicalRecord;
import com.khush.exception.MedicalRecordNotFoundException;
import com.khush.service.MedicalRecordService;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping("/getAll")
	public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
		List<MedicalRecord> allMedicalRecords = medicalRecordService.getAllMedicalRecords();
		return new ResponseEntity<>(allMedicalRecords, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecord medicalRecord2 = medicalRecordService.createMedicalRecord(medicalRecord);

		return new ResponseEntity<MedicalRecord>(medicalRecord2, HttpStatus.CREATED);

	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(id);

		return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id,
			@RequestBody MedicalRecord updatedMedicalRecord) {
		try {
			MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord(id, updatedMedicalRecord);
			return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);
		} catch (MedicalRecordNotFoundException e) {
			return new ResponseEntity<MedicalRecord>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
		try {
			medicalRecordService.deletemedicalRecord(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (MedicalRecordNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getByPatient/{patientId}")
	public ResponseEntity<MedicalRecord> getMedicalRecordsByPatient(@PathVariable Long patientId) {

		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordsByPatient(patientId);

		return new ResponseEntity<MedicalRecord>(medicalRecord, HttpStatus.OK);

	}

	@GetMapping("/getByDiagnosis/{diagnosis}")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByDiagnosis(@PathVariable String diagnosis) {
		List<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordsByDiagnosis(diagnosis);

		return new ResponseEntity<List<MedicalRecord>>(medicalRecord, HttpStatus.OK);

	}

	@GetMapping("/getByMedications/{medications}")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByMedications(@PathVariable String medications) {
		List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByMedications(medications);

		return new ResponseEntity<List<MedicalRecord>>(medicalRecords, HttpStatus.OK);

	}

	@GetMapping("/getByAllergies/{allergies}")
	public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByAllergies(@PathVariable String allergies) {

		List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByAllergies(allergies);
		return new ResponseEntity<List<MedicalRecord>>(medicalRecords, HttpStatus.OK);

	}
}
