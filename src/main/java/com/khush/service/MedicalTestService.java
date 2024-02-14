package com.khush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khush.entity.MedicalTest;
import com.khush.repository.MedicalTestRepository;

@Service
public class MedicalTestService {
	@Autowired
    MedicalTestRepository medicalTestRepository;

	public List<MedicalTest> getAllMedicalTest() {
		// TODO Auto-generated method stub
		return medicalTestRepository.findAll();
	}

}
