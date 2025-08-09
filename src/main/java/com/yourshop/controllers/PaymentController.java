package com.yourshop.controllers;

import com.yourshop.dto.payment.PaymentResponse;
import com.yourshop.dto.payment.PaymentWebhookRequest;
import com.yourshop.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<PaymentResponse> webhook(@Valid @RequestBody PaymentWebhookRequest request) {
        return ResponseEntity.ok(paymentService.handleWebhook(request));
    }
}