package com.widetech.interview.widetech_interview.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String productType;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
    private BigDecimal total;
}
