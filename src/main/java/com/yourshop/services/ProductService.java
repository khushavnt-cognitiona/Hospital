package com.yourshop.services;

import com.yourshop.dto.product.ProductRequest;
import com.yourshop.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
    ProductResponse get(Long id);
    Page<ProductResponse> search(String q, Long categoryId, Double minPrice, Double maxPrice, Pageable pageable);
}