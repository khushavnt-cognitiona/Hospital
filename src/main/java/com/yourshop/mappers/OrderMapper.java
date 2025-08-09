package com.yourshop.mappers;

import com.yourshop.dto.order.OrderItemResponse;
import com.yourshop.dto.order.OrderResponse;
import com.yourshop.entities.OrderEntity;
import com.yourshop.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponse toResponse(OrderEntity order);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    OrderItemResponse toItemResponse(OrderItem item);
}