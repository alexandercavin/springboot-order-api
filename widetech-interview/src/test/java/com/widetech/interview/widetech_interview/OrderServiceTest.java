package com.widetech.interview.widetech_interview;

import com.widetech.interview.widetech_interview.order.dto.AddToCartRequestDTO;
import com.widetech.interview.widetech_interview.order.dto.OrderResponseDTO;
import com.widetech.interview.widetech_interview.order.entity.OrderEntity;
import com.widetech.interview.widetech_interview.order.entity.OrderStatus;
import com.widetech.interview.widetech_interview.order.repository.OrderRepository;
import com.widetech.interview.widetech_interview.order.service.OrderService;
import com.widetech.interview.widetech_interview.product.entity.Product;
import com.widetech.interview.widetech_interview.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Test
    void addToCart_shouldAddNewItem_whenProductNotInCart() {
        // Arrange
        OrderEntity cart = OrderEntity.builder()
                .status(OrderStatus.PENDING)
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(BigDecimal.valueOf(100))
                .build();

        AddToCartRequestDTO request = new AddToCartRequestDTO();
        request.setProductId(1L);
        request.setQuantity(2);

        when(orderRepository.findTopByStatusOrderByCreatedAtDesc(OrderStatus.PENDING))
                .thenReturn(Optional.of(cart));

        when(productService.getProductEntityById(1L))
                .thenReturn(product);

        when(orderRepository.save(cart))
                .thenReturn(cart);

        // Act
        OrderResponseDTO response = orderService.addToCart(request);

        // Assert
        assertEquals(1, response.getItems().size());
        assertEquals(2, response.getItems().get(0).getQuantity());
        assertEquals(BigDecimal.valueOf(200), response.getGrandTotal());
    }
}
