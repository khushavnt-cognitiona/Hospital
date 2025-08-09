package com.yourshop.services;

import com.yourshop.dto.review.ReviewRequest;
import com.yourshop.dto.review.ReviewResponse;

public interface ReviewService {
    ReviewResponse add(ReviewRequest request);
    ReviewResponse approve(Long id);
}