package com.eventorganizer.payment.service;

import com.eventorganizer.payment.dto.PaymentRequestDTO;
import com.eventorganizer.payment.dto.PaymentResponseDTO;
import com.eventorganizer.payment.model.Payment;
import com.eventorganizer.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());

        payment = paymentRepository.save(payment);

        boolean success = Math.random() > 0.2;
        payment.setStatus(success ? "PAID" : "FAILED");
        payment = paymentRepository.save(payment);

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getStatus(),
                success ? "Payment successful. Receipt #" + payment.getId() : "Payment failed. Please try again."
        );
    }
}
