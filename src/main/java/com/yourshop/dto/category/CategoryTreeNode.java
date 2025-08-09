package com.yourshop.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTreeNode {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private List<CategoryTreeNode> children;
}