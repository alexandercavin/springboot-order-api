package com.widetech.interview.widetech_interview.order.service;


import com.widetech.interview.widetech_interview.order.dto.AddToCartRequestDTO;
import com.widetech.interview.widetech_interview.order.dto.OrderResponseDTO;
import com.widetech.interview.widetech_interview.order.dto.PlaceOrderRequestDTO;
import com.widetech.interview.widetech_interview.order.entity.OrderEntity;
import com.widetech.interview.widetech_interview.order.entity.OrderItem;
import com.widetech.interview.widetech_interview.order.entity.OrderStatus;
import com.widetech.interview.widetech_interview.order.mapper.OrderMapper;
import com.widetech.interview.widetech_interview.order.repository.OrderRepository;
import com.widetech.interview.widetech_interview.product.dto.ProductResponseDTO;
import com.widetech.interview.widetech_interview.product.entity.Product;
import com.widetech.interview.widetech_interview.product.mapper.ProductMapper;
import com.widetech.interview.widetech_interview.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    //cart

    public OrderResponseDTO getCart() {
        OrderEntity order = getOrCreatePendingCart();
        return OrderMapper.toResponse(order);

    }

    public OrderResponseDTO addToCart(AddToCartRequestDTO request) {

        OrderEntity cart = getOrCreatePendingCart();

        Product product = productService.getProductEntityById(request.getProductId());

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        existingItem -> existingItem.setQuantity(
                                existingItem.getQuantity() + request.getQuantity()
                        ),
                        () -> {
                            OrderItem newItem = OrderItem.builder()
                                    .order(cart)
                                    .product(product)
                                    .quantity(request.getQuantity())
                                    .priceAtPurchase(product.getPrice())
                                    .build();

                            cart.getItems().add(newItem);
                        }
                );

        OrderEntity savedCart = orderRepository.save(cart);

        return OrderMapper.toResponse(savedCart);
    }

    public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request)  {

        OrderEntity cart = getOrCreatePendingCart();
        if(cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        cart.setStatus(OrderStatus.PLACED);
        cart.setCustomerName(request.getCustomerName());
        cart.setAddress(request.getAddress());

        OrderEntity savedCart = orderRepository.save(cart);

        return OrderMapper.toResponse(savedCart);
    }

    public OrderResponseDTO getOrderById(Long id) {
        return OrderMapper.toResponse(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }
    //helper functions
    private OrderEntity getOrCreatePendingCart() {
        return orderRepository
                .findTopByStatusOrderByCreatedAtDesc(OrderStatus.PENDING)
                .orElseGet(() -> {
                    OrderEntity newCart = OrderEntity.builder()
                            .status(OrderStatus.PENDING)
                            .build();
                    return orderRepository.save(newCart);
                });
    }

}
