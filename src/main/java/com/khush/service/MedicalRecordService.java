package com.khush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.khush.entity.MedicalRecord;
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

}
