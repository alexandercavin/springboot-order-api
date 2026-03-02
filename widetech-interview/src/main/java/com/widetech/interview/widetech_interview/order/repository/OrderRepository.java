package com.widetech.interview.widetech_interview.order.repository;

import com.widetech.interview.widetech_interview.order.entity.OrderEntity;
import com.widetech.interview.widetech_interview.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findTopByStatusOrderByCreatedAtDesc(OrderStatus status);
}
