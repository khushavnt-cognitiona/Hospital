package com.khush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khush.entity.MedicalTest;
import com.khush.service.MedicalTestService;

@RestController
@RequestMapping("/medical-tests") 
public class MedicalTestController {

    @Autowired
    MedicalTestService medicalTestService;

    @GetMapping("/getAll") 
    public ResponseEntity<List<MedicalTest>> getAllMedicalTest() {
        List<MedicalTest> medicalTests = medicalTestService.getAllMedicalTest();
        return new ResponseEntity<>(medicalTests, HttpStatus.OK);
    }

   

}
