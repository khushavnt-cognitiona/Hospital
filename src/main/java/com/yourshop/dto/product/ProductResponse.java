package com.yourshop.dto.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Integer stockQuantity;
    private List<String> images;
    private Long categoryId;
    private String categoryName;
    private Long vendorId;
    private String vendorName;
    private String attributes;
    private Double avgRating;
}