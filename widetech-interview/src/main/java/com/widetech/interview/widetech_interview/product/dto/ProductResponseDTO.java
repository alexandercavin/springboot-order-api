package com.widetech.interview.widetech_interview.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String type;
    private BigDecimal price;
}
