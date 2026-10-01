package com.saga.order.service;

import com.saga.order.dto.CreateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderRequest request) {

        Order order = new Order(
                request.getProductId(),
                request.getQuantity(),
                request.getAmount(),
                "CREATED"
        );

        return orderRepository.save(order);
    }

    public Order updateStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}
