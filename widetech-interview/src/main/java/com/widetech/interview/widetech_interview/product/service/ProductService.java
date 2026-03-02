package com.widetech.interview.widetech_interview.product.service;

import com.widetech.interview.widetech_interview.product.dto.ProductRequestDTO;
import com.widetech.interview.widetech_interview.product.dto.ProductResponseDTO;
import com.widetech.interview.widetech_interview.product.entity.Product;
import com.widetech.interview.widetech_interview.product.mapper.ProductMapper;
import com.widetech.interview.widetech_interview.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        if (page < 0 || size <= 0 || size > 100) {
            throw new IllegalArgumentException("Invalid pagination params");
        }
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return productPage.map(ProductMapper::toDTO);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDTO(product);
    }

    //for orderservice
    public Product getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponseDTO create(ProductRequestDTO request) {
//        Product product = Product.builder()
//                .name(request.getName())
//                .description(request.getDescription())
//                .quantity(request.getQuantity())
//                .type(request.getType())
//                .price(request.getPrice())
//                .build();
        Product product = ProductMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);

    }

    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setType(request.getType());
        product.setPrice(request.getPrice());
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    public void delete(Long id) {

        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Cannot delete product because it is used in orders.");
        }
    }




}
