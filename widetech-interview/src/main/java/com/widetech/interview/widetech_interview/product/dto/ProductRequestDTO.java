package com.widetech.interview.widetech_interview.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Type is required")
    private String type;


    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

}
