package com.widetech.interview.widetech_interview.product.mapper;

import com.widetech.interview.widetech_interview.product.dto.ProductRequestDTO;
import com.widetech.interview.widetech_interview.product.dto.ProductResponseDTO;
import com.widetech.interview.widetech_interview.product.entity.Product;

public class ProductMapper {
    public static ProductResponseDTO toDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .type(dto.getType())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
