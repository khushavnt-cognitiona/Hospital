// AdminRepository.java
package com.khush.repository;

import com.khush.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can add custom query methods if needed
}
