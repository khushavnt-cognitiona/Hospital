package com.yourshop.services;

import com.yourshop.dto.category.CategoryRequest;
import com.yourshop.dto.category.CategoryResponse;
import com.yourshop.dto.category.CategoryTreeNode;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
    CategoryResponse get(Long id);
    List<CategoryTreeNode> getTree();
}