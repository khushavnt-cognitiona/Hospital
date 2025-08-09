package com.yourshop.services.impl;

import com.yourshop.dto.review.ReviewRequest;
import com.yourshop.dto.review.ReviewResponse;
import com.yourshop.entities.Product;
import com.yourshop.entities.Review;
import com.yourshop.entities.User;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.ReviewMapper;
import com.yourshop.repositories.ProductRepository;
import com.yourshop.repositories.ReviewRepository;
import com.yourshop.repositories.UserRepository;
import com.yourshop.services.ReviewService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.reviewMapper = reviewMapper;
    }

    private User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    @Transactional
    public ReviewResponse add(ReviewRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        User user = currentUser();
        Review review = Review.builder()
                .product(product)
                .user(user)
                .rating(request.getRating())
                .title(request.getTitle())
                .body(request.getBody())
                .approved(false)
                .build();
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public ReviewResponse approve(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Review not found"));
        review.setApproved(true);
        Review saved = reviewRepository.save(review);
        // update avg rating
        Product product = saved.getProduct();
        var reviews = reviewRepository.findByProduct(product).stream().filter(Review::isApproved).toList();
        double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0);
        product.setAvgRating(avg);
        productRepository.save(product);
        return reviewMapper.toResponse(saved);
    }
}