package com.khush.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khush.entity.MedicalRecord;

@ResponseBody
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	Optional<MedicalRecord> findByPatientId(Long patientId);

	List<MedicalRecord> findByDiagnosis(String diagnosis);

	List<MedicalRecord> findByMedicationsContaining(String medications);

	List<MedicalRecord> getMedicalRecordsByAllergies(String allergies);
	
	
	

}
