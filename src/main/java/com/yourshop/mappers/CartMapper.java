package com.yourshop.mappers;

import com.yourshop.dto.cart.CartItemResponse;
import com.yourshop.dto.cart.CartResponse;
import com.yourshop.entities.Cart;
import com.yourshop.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "total", expression = "java(cart.getItems().stream().map(i -> i.getPriceSnapshot().multiply(new java.math.BigDecimal(i.getQuantity()))).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add))")
    CartResponse toResponse(Cart cart);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    CartItemResponse toItemResponse(CartItem item);
}