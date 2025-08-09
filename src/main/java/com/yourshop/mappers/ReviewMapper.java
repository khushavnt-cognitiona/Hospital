package com.yourshop.mappers;

import com.yourshop.dto.review.ReviewResponse;
import com.yourshop.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "product.id")
    ReviewResponse toResponse(Review review);
}