package com.saga.order.controller;

import com.saga.order.dto.CreateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @PutMapping("/{orderId}/status")
    public Order updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return orderService.updateStatus(orderId, status);
    }
}
