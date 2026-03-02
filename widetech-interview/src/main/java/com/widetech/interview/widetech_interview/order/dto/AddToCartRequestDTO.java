package com.widetech.interview.widetech_interview.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDTO {
    @NotNull
    private Long productId;

    @NotNull
    @Positive
    @Min(1)
    private Integer quantity;
}
