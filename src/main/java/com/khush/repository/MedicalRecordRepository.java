package com.khush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khush.entity.MedicalRecord;

@ResponseBody
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	
	
	

}
