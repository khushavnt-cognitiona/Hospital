package com.khush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.khush.entity.MedicalRecord;
import com.khush.exception.MedicalRecordNotFoundException;
import com.khush.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecordRepository.findAll();

	}

	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {

		return medicalRecordRepository.save(medicalRecord);
	}

	public MedicalRecord getMedicalRecordById(Long id) {
		Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);

		if (medicalRecord.isPresent()) {

			return medicalRecord.get();
		} else {
			throw new MedicalRecordNotFoundException("Medical Record not found with ID:" + id);

		}

	}

	public MedicalRecord updateMedicalRecord(Long id, MedicalRecord updatedMedicalRecord)
			throws MedicalRecordNotFoundException {
		Optional<MedicalRecord> existingMedicalRecordOptional = medicalRecordRepository.findById(id);

		if (existingMedicalRecordOptional.isPresent()) {
			MedicalRecord existingMedicalRecord = existingMedicalRecordOptional.get();

			// Update fields with the new values
			existingMedicalRecord.setDate(updatedMedicalRecord.getDate());
			existingMedicalRecord.setAllergies(updatedMedicalRecord.getAllergies());
			existingMedicalRecord.setDiagnosis(updatedMedicalRecord.getDiagnosis());
			existingMedicalRecord.setMedications(updatedMedicalRecord.getMedications());
			existingMedicalRecord.setPatient(updatedMedicalRecord.getPatient());
			existingMedicalRecord.setNotes(updatedMedicalRecord.getNotes());
			existingMedicalRecord.setTreatment(updatedMedicalRecord.getTreatment());

			return medicalRecordRepository.save(existingMedicalRecord);
		} else {
			throw new MedicalRecordNotFoundException("Medical record not found with id: " + id);
		}
	}

	public void deletemedicalRecord(Long id) {
		Optional<MedicalRecord> optional = medicalRecordRepository.findById(id);
		if (optional.isPresent()) {

			medicalRecordRepository.deleteById(id);
		} else {
			throw new MedicalRecordNotFoundException("Medical record not found with ID: " + id);
		}

	}

	public MedicalRecord getMedicalRecordsByPatient(Long patientId) {
		Optional<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientId(patientId);
		if (medicalRecords.isPresent()) {
			MedicalRecord medicalRecord = medicalRecords.get();

			return medicalRecord;
		} else {
			throw new MedicalRecordNotFoundException("MedicalRecord Not Found with this PatientId :" + patientId);
		}

	}

	public List<MedicalRecord> getMedicalRecordsByDiagnosis(String diagnosis) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findByDiagnosis(diagnosis);

		if (!medicalRecords.isEmpty()) {

			return medicalRecords;
		} else {
			throw new MedicalRecordNotFoundException("No medical records found with diagnosis: " + diagnosis);
		}

	}

	public List<MedicalRecord> getMedicalRecordsByMedications(String medications) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.findByMedicationsContaining(medications);
		if (!medicalRecords.isEmpty()) {
			return medicalRecords;
		} else {
			throw new MedicalRecordNotFoundException("No Medical Record Found With  This Medication: " + medications);
		}

	}

	public List<MedicalRecord> getMedicalRecordsByAllergies(String allergies) {
		List<MedicalRecord> medicalRecords = medicalRecordRepository.getMedicalRecordsByAllergies(allergies);
		if (!medicalRecords.isEmpty()) {

			return medicalRecords;
		} else {
			throw new MedicalRecordNotFoundException("Medical Record Not Found With this allergies  : " + allergies);
		}

	}

}
