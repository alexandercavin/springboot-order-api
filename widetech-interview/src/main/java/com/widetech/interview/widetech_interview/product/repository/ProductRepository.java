package com.widetech.interview.widetech_interview.product.repository;

import com.widetech.interview.widetech_interview.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
