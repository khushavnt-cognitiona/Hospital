package com.khush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khush.entity.Patient;

@Repository
public interface PatiensRepository extends JpaRepository<Patient, Long> {

}
