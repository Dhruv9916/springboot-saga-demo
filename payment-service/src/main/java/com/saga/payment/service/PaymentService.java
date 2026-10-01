package com.saga.payment.service;

import com.saga.payment.dto.PaymentRequest;
import com.saga.payment.entity.Payment;
import com.saga.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(PaymentRequest request) {

        Payment payment = new Payment(
                request.getOrderId(),
                request.getAmount(),
                "SUCCESS"
        );

        System.out.println(
                "Payment successful for order: " + request.getOrderId()
        );

        return paymentRepository.save(payment);
    }

    public Payment refund(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus("REFUNDED");

        System.out.println(
                "Payment refunded for order: " + payment.getOrderId()
        );

        return paymentRepository.save(payment);
    }
}
