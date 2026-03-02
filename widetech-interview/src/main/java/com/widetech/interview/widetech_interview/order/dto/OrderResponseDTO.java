package com.widetech.interview.widetech_interview.order.dto;


import com.widetech.interview.widetech_interview.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private String customerName;
    private String address;
    private OrderStatus status;
    private List<OrderItemResponseDTO> items;
    private BigDecimal grandTotal;
    private LocalDateTime createdAt;
    private LocalDateTime placedAt;

}
