package com.yourshop.services.impl;

import com.yourshop.dto.product.ProductRequest;
import com.yourshop.dto.product.ProductResponse;
import com.yourshop.entities.Category;
import com.yourshop.entities.Product;
import com.yourshop.entities.Vendor;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.ProductMapper;
import com.yourshop.repositories.CategoryRepository;
import com.yourshop.repositories.ProductRepository;
import com.yourshop.repositories.VendorRepository;
import com.yourshop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              VendorRepository vendorRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Vendor vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new NotFoundException("Vendor not found"));
        Product product = Product.builder()
                .name(request.getName())
                .sku(request.getSku())
                .description(request.getDescription())
                .price(request.getPrice())
                .discountPrice(request.getDiscountPrice())
                .stockQuantity(request.getStockQuantity())
                .images(request.getImages())
                .category(category)
                .vendor(vendor)
                .attributes(request.getAttributes())
                .build();
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        productMapper.updateEntityFromRequest(request, product);
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            product.setCategory(category);
        }
        if (request.getVendorId() != null) {
            Vendor vendor = vendorRepository.findById(request.getVendorId())
                    .orElseThrow(() -> new NotFoundException("Vendor not found"));
            product.setVendor(vendor);
        }
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) throw new NotFoundException("Product not found");
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse get(Long id) {
        return productRepository.findById(id).map(productMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Page<ProductResponse> search(String q, Long categoryId, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);
        if (q != null && !q.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%"));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId));
        }
        if (minPrice != null) {
            spec = spec.and((root, query, cb) -> cb.ge(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) -> cb.le(root.get("price"), maxPrice));
        }
        return productRepository.findAll(spec, pageable).map(productMapper::toResponse);
    }
}