package com.yourshop.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryRequest {
    @NotBlank
    private String name;
    private String description;
    private Long parentId;
}

@Data
class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
}

@Data
class CategoryTreeNode {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private List<CategoryTreeNode> children;
}