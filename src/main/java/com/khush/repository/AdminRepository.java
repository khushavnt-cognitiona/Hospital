// AdminRepository.java
package com.khush.repository;

import com.khush.entity.Admin;
import com.khush.entity.AdminRole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	List<Admin> findByRole(AdminRole role);
    // You can add custom query methods if needed
}
