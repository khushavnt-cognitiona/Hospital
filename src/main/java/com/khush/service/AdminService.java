
// AdminService.java
package com.khush.service;

import com.khush.entity.Admin;
import com.khush.entity.AdminRole;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface AdminService {
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(Long id);
    Admin createAdmin(Admin admin);
    void deleteAdmin(Long id);
    Admin updateAdmin(Long id, Admin admin);
    List<Admin> getAdminsByRole(AdminRole role);
}
