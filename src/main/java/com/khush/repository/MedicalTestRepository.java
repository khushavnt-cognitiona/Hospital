package com.khush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khush.entity.MedicalTest;

@Repository
public interface MedicalTestRepository  extends JpaRepository<MedicalTest, Long>{

}
