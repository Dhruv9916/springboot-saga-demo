package com.saga.payment.controller;

import com.saga.payment.dto.PaymentRequest;
import com.saga.payment.entity.Payment;
import com.saga.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment processPayment(
            @RequestBody PaymentRequest request) {

        return paymentService.processPayment(request);
    }

    @PutMapping("/{paymentId}/refund")
    public Payment refund(@PathVariable Long paymentId) {

        return paymentService.refund(paymentId);
    }
}
