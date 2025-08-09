package com.yourshop.services.impl;

import com.yourshop.dto.order.CheckoutRequest;
import com.yourshop.dto.order.OrderResponse;
import com.yourshop.dto.order.OrderStatusUpdateRequest;
import com.yourshop.entities.*;
import com.yourshop.entities.enums.OrderStatus;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.OrderMapper;
import com.yourshop.repositories.CartRepository;
import com.yourshop.repositories.OrderRepository;
import com.yourshop.repositories.UserRepository;
import com.yourshop.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    private User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    @Transactional
    public OrderResponse checkout(CheckoutRequest request) {
        User user = currentUser();
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Cart not found"));
        if (cart.getItems().isEmpty()) throw new NotFoundException("Cart is empty");
        BigDecimal total = cart.getItems().stream()
                .map(i -> i.getPriceSnapshot().multiply(new BigDecimal(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        OrderEntity order = OrderEntity.builder()
                .orderNumber(UUID.randomUUID().toString())
                .user(user)
                .status(OrderStatus.CREATED)
                .totalAmount(total)
                .shippingAddress(request.getShippingAddress())
                .build();
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = OrderItem.builder()
                    .order(order)
                    .product(ci.getProduct())
                    .quantity(ci.getQuantity())
                    .price(ci.getPriceSnapshot())
                    .build();
            order.getItems().add(oi);
        }
        OrderEntity saved = orderRepository.save(order);
        cart.getItems().clear();
        cartRepository.save(cart);
        return orderMapper.toResponse(saved);
    }

    @Override
    public OrderResponse getById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public Page<OrderResponse> listMyOrders(Pageable pageable) {
        User user = currentUser();
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toResponse)
                .collect(java.util.stream.Collectors.collectingAndThen(java.util.stream.Collectors.toList(),
                        list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())));
    }

    @Override
    public Page<OrderResponse> listAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toResponse);
    }

    @Override
    @Transactional
    public OrderResponse updateStatus(Long id, OrderStatusUpdateRequest request) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(request.getStatus());
        return orderMapper.toResponse(orderRepository.save(order));
    }
}