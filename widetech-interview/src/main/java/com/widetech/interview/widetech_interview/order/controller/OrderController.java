package com.widetech.interview.widetech_interview.order.controller;

import com.widetech.interview.widetech_interview.order.dto.AddToCartRequestDTO;
import com.widetech.interview.widetech_interview.order.dto.OrderResponseDTO;
import com.widetech.interview.widetech_interview.order.dto.PlaceOrderRequestDTO;
import com.widetech.interview.widetech_interview.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/cart")
    public ResponseEntity<OrderResponseDTO> getCart() {
        return ResponseEntity.ok(orderService.getCart());
    }

    @PostMapping("/cart/items")
    public ResponseEntity<OrderResponseDTO> addToCart(@Valid @RequestBody AddToCartRequestDTO request) {
        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addToCart(request));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDTO> placeOrder(@Valid @RequestBody PlaceOrderRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(request));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
