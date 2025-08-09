package com.yourshop.services.impl;

import com.yourshop.dto.payment.PaymentResponse;
import com.yourshop.dto.payment.PaymentWebhookRequest;
import com.yourshop.entities.OrderEntity;
import com.yourshop.entities.Payment;
import com.yourshop.entities.enums.OrderStatus;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.PaymentMapper;
import com.yourshop.repositories.OrderRepository;
import com.yourshop.repositories.PaymentRepository;
import com.yourshop.services.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentResponse handleWebhook(PaymentWebhookRequest request) {
        OrderEntity order = orderRepository.findByOrderNumber(request.getOrderNumber())
                .orElseThrow(() -> new NotFoundException("Order not found"));
        Payment payment = Payment.builder()
                .order(order)
                .provider(request.getProvider())
                .status(request.getStatus())
                .transactionId(request.getTransactionId())
                .build();
        if ("SUCCESS".equalsIgnoreCase(request.getStatus())) {
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }
}