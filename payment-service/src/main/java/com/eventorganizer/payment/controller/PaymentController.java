package com.eventorganizer.payment.controller;

import com.eventorganizer.payment.dto.PaymentRequestDTO;
import com.eventorganizer.payment.dto.PaymentResponseDTO;
import com.eventorganizer.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> makePayment(@RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.processPayment(dto));
    }
}
