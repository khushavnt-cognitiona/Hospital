package com.khush.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khush.entity.Admin;
import com.khush.entity.AdminRole;
import com.khush.exception.AdminNotFoundException;
import com.khush.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getAdminById(Long id) {
		Optional<Admin> adminOptional = adminRepository.findById(id);

		if (adminOptional.isPresent()) {
			return adminOptional;
		} else {
			throw new AdminNotFoundException("Admin with ID " + id + " not found");
		}
	}

	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdmin(Long id) {
		Optional<Admin> optional = adminRepository.findById(id);

		if (optional.isPresent()) {
			adminRepository.deleteById(id);
		} else {
			throw new AdminNotFoundException("Admin with ID " + id + " not found");
		}
	}

	@Override
	public Admin updateAdmin(Long id, Admin adminDetails) {
		Optional<Admin> optional = adminRepository.findById(id);

		if (optional.isPresent()) {
			Admin existingAdmin = optional.get();
			existingAdmin.setName(adminDetails.getName());
			existingAdmin.setUsername(adminDetails.getUsername());
			existingAdmin.setPassword(adminDetails.getPassword());

			return adminRepository.save(existingAdmin);
		} else {
			throw new AdminNotFoundException("Admin with ID " + id + " not found");
		}
	}

	@Override
	public List<Admin> getAdminsByRole(AdminRole role) {
		return adminRepository.findByRole(role);
	}
}
