package com.khush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khush.entity.Admin;
import com.khush.exception.AdminNotFoundException;
import com.khush.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/get")
	public ResponseEntity<List<Admin>> getAllAdmin() {
		List<Admin> admins = adminService.getAllAdmins();

		return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);

	}

	@PostMapping("/post")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
		Admin admin2 = adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(admin2, HttpStatus.CREATED);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {

		try {
			Admin admin = adminService.getAdminById(id)
					.orElseThrow(() -> new AdminNotFoundException("Admin with ID " + id + " not found"));
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);

		} catch (AdminNotFoundException e) {

			throw new AdminNotFoundException("Admin with ID " + id + " not found");

		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
		try {
			adminService.deleteAdmin(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (AdminNotFoundException e) {
			return new ResponseEntity<>("Admin with ID " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
		Admin existingAdmin = adminService.getAdminById(id)
				.orElseThrow(() -> new AdminNotFoundException("Admin with ID " + id + " not found"));

		existingAdmin.setName(adminDetails.getName());
		existingAdmin.setUsername(adminDetails.getUsername());
		existingAdmin.setPassword(adminDetails.getPassword());

		Admin updatedAdmin = adminService.updateAdmin(id,existingAdmin);
		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	}

}
