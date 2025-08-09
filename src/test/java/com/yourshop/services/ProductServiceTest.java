package com.yourshop.services;

import com.yourshop.dto.product.ProductRequest;
import com.yourshop.entities.Category;
import com.yourshop.entities.Product;
import com.yourshop.entities.Vendor;
import com.yourshop.mappers.ProductMapper;
import com.yourshop.repositories.CategoryRepository;
import com.yourshop.repositories.ProductRepository;
import com.yourshop.repositories.VendorRepository;
import com.yourshop.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;
    private ProductMapper productMapper;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        vendorRepository = mock(VendorRepository.class);
        productMapper = mock(ProductMapper.class);
        productService = new ProductServiceImpl(productRepository, categoryRepository, vendorRepository, productMapper);
    }

    @Test
    void create_success() {
        ProductRequest req = new ProductRequest();
        req.setName("Phone");
        req.setSku("SKU123");
        req.setPrice(BigDecimal.valueOf(100));
        req.setStockQuantity(10);
        req.setCategoryId(1L);
        req.setVendorId(2L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(Category.builder().id(1L).name("Cat").build()));
        when(vendorRepository.findById(2L)).thenReturn(Optional.of(Vendor.builder().id(2L).companyName("Vend").build()));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));
        productService.create(req);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void search_invokes_spec() {
        when(productRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of()));
        Page<?> page = productService.search("phone", 1L, 10.0, 500.0, PageRequest.of(0, 10));
        assertNotNull(page);
        verify(productRepository).findAll(any(Specification.class), any(PageRequest.class));
    }
}