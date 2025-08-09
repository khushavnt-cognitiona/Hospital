package com.yourshop.dto.review;

import lombok.Data;

import java.time.Instant;

@Data
public class ReviewResponse {
    private Long id;
    private Integer rating;
    private String title;
    private String body;
    private Long userId;
    private Long productId;
    private boolean approved;
    private Instant createdAt;
}