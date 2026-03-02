package com.widetech.interview.widetech_interview.order.mapper;

import com.widetech.interview.widetech_interview.order.dto.OrderItemResponseDTO;
import com.widetech.interview.widetech_interview.order.dto.OrderResponseDTO;
import com.widetech.interview.widetech_interview.order.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toResponse(OrderEntity order) {

        List<OrderItemResponseDTO> items = order.getItems().stream()
                .map(item -> OrderItemResponseDTO.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .productType(item.getProduct().getType())
                        .quantity(item.getQuantity())
                        .priceAtPurchase(item.getPriceAtPurchase())
                        .total(item.getPriceAtPurchase()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .toList();

        BigDecimal grandTotal = items.stream()
                .map(OrderItemResponseDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .address(order.getAddress())
                .status(order.getStatus())
                .items(items)
                .grandTotal(grandTotal)
                .createdAt(order.getCreatedAt())
                .build();
    }
}
