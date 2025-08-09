package com.yourshop.services.impl;

import com.yourshop.dto.cart.AddCartItemRequest;
import com.yourshop.dto.cart.CartResponse;
import com.yourshop.dto.cart.UpdateCartItemRequest;
import com.yourshop.entities.*;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.CartMapper;
import com.yourshop.repositories.CartRepository;
import com.yourshop.repositories.ProductRepository;
import com.yourshop.repositories.UserRepository;
import com.yourshop.services.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = Cart.builder().user(user).build();
            return cartRepository.save(c);
        });
    }

    private User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public CartResponse getMyCart() {
        User user = currentUser();
        Cart cart = getOrCreateCart(user);
        return cartMapper.toResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse addItem(AddCartItemRequest request) {
        User user = currentUser();
        Cart cart = getOrCreateCart(user);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));
        CartItem existing = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
        BigDecimal price = product.getDiscountPrice() != null ? product.getDiscountPrice() : product.getPrice();
        if (existing == null) {
            CartItem item = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .priceSnapshot(price)
                    .build();
            cart.getItems().add(item);
        } else {
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            existing.setPriceSnapshot(price);
        }
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponse updateItem(UpdateCartItemRequest request) {
        User user = currentUser();
        Cart cart = getOrCreateCart(user);
        CartItem item = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(request.getProductId())).findFirst()
                .orElseThrow(() -> new NotFoundException("Item not in cart"));
        if (request.getQuantity() <= 0) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(request.getQuantity());
        }
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponse removeItem(Long productId) {
        User user = currentUser();
        Cart cart = getOrCreateCart(user);
        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void clear() {
        User user = currentUser();
        Cart cart = getOrCreateCart(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}