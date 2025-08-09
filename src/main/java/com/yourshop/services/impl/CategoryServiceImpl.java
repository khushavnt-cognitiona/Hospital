package com.yourshop.services.impl;

import com.yourshop.dto.category.CategoryRequest;
import com.yourshop.dto.category.CategoryResponse;
import com.yourshop.dto.category.CategoryTreeNode;
import com.yourshop.entities.Category;
import com.yourshop.exceptions.NotFoundException;
import com.yourshop.mappers.CategoryMapper;
import com.yourshop.repositories.CategoryRepository;
import com.yourshop.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parentId(request.getParentId())
                .build();
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        if (request.getName() != null) category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setParentId(request.getParentId());
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) throw new NotFoundException("Category not found");
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse get(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public List<CategoryTreeNode> getTree() {
        List<Category> all = categoryRepository.findAll();
        Map<Long, CategoryTreeNode> map = new HashMap<>();
        for (Category c : all) {
            CategoryTreeNode node = new CategoryTreeNode();
            node.setId(c.getId());
            node.setName(c.getName());
            node.setDescription(c.getDescription());
            node.setParentId(c.getParentId());
            node.setChildren(new ArrayList<>());
            map.put(c.getId(), node);
        }
        List<CategoryTreeNode> roots = new ArrayList<>();
        for (Category c : all) {
            CategoryTreeNode node = map.get(c.getId());
            if (c.getParentId() == null) {
                roots.add(node);
            } else {
                CategoryTreeNode parent = map.get(c.getParentId());
                if (parent != null) parent.getChildren().add(node);
                else roots.add(node);
            }
        }
        return roots;
    }
}