package com.yourshop.dto.product;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String sku;
    private String description;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal discountPrice;
    @NotNull
    @Min(0)
    private Integer stockQuantity;
    private List<String> images;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long vendorId;
    private String attributes; // JSON string
}