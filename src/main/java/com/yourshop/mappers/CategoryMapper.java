package com.yourshop.mappers;

import com.yourshop.dto.category.CategoryResponse;
import com.yourshop.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
}