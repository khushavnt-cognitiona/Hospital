package com.yourshop.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotNull
    private Long productId;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    private String title;
    @NotBlank
    private String body;
}