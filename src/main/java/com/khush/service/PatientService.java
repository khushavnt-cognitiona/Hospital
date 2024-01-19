package com.khush.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khush.entity.Patient;
import com.khush.exception.PatientNotFoundException;
import com.khush.repository.PatiensRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    PatiensRepository patiensRepository;

    public List<Patient> getAllPatients() {
        return patiensRepository.findAll();
    }

    public Patient createPatient(Patient patient) {
        return patiensRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) throws PatientNotFoundException {
        Optional<Patient> optional = patiensRepository.findById(id);

        if (optional.isPresent()) {
            Patient existingPatient = optional.get();

            // Update patient details
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setDob(patient.getDob());
            existingPatient.setPhoneNumber(patient.getPhoneNumber());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setBloodtype(patient.getBloodtype());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setEmergencyContact(patient.getEmergencyContact());
            existingPatient.setEmergencyphone(patient.getEmergencyphone());
            existingPatient.setGendar(patient.getGendar());

            return patiensRepository.save(existingPatient);
        }

        throw new PatientNotFoundException("Patient not found with ID: " + id);
    }

    public void deletePatient(long id) throws PatientNotFoundException {
        Optional<Patient> optional = patiensRepository.findById(id);

        if (optional.isPresent()) {
            patiensRepository.deleteById(id);
        } else {
            throw new PatientNotFoundException("Patient not associated with ID: " + id);
        }
    }

    public List<Patient> getPatientsByGender(String gender) {
        return patiensRepository.findAll()
                .stream()
                .filter(patient -> gender.equalsIgnoreCase(patient.getGendar()))
                .collect(Collectors.toList());
    }
}
