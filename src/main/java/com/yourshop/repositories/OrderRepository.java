package com.yourshop.repositories;

import com.yourshop.entities.OrderEntity;
import com.yourshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    List<OrderEntity> findByUser(User user);
    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}