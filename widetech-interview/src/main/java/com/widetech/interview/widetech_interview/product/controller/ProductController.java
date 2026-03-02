package com.widetech.interview.widetech_interview.product.controller;

import com.widetech.interview.widetech_interview.product.dto.ProductRequestDTO;
import com.widetech.interview.widetech_interview.product.dto.ProductResponseDTO;
import com.widetech.interview.widetech_interview.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
