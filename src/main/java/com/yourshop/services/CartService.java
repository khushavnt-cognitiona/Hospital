package com.yourshop.services;

import com.yourshop.dto.cart.AddCartItemRequest;
import com.yourshop.dto.cart.CartResponse;
import com.yourshop.dto.cart.UpdateCartItemRequest;

public interface CartService {
    CartResponse getMyCart();
    CartResponse addItem(AddCartItemRequest request);
    CartResponse updateItem(UpdateCartItemRequest request);
    CartResponse removeItem(Long productId);
    void clear();
}