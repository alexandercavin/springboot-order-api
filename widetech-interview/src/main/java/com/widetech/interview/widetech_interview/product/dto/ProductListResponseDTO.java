package com.widetech.interview.widetech_interview.product.dto;

import java.util.List;

//for get all products to remove metadata
public class ProductListResponseDTO {

    private List<ProductResponseDTO> products;

    // Constructors, getters, setters
    public ProductListResponseDTO(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }
}
