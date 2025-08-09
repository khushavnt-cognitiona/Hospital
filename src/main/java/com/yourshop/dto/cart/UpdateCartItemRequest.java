package com.yourshop.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull
    private Long productId;
    @NotNull
    @Min(0)
    private Integer quantity;
}