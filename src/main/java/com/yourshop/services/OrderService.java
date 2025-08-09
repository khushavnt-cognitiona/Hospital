package com.yourshop.services;

import com.yourshop.dto.order.CheckoutRequest;
import com.yourshop.dto.order.OrderResponse;
import com.yourshop.dto.order.OrderStatusUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse checkout(CheckoutRequest request);
    OrderResponse getById(Long id);
    Page<OrderResponse> listMyOrders(Pageable pageable);
    Page<OrderResponse> listAll(Pageable pageable);
    OrderResponse updateStatus(Long id, OrderStatusUpdateRequest request);
}