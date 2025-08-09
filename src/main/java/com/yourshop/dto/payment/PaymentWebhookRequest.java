package com.yourshop.dto.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentWebhookRequest {
    @NotBlank
    private String provider;
    @NotBlank
    private String transactionId;
    @NotBlank
    private String status; // SUCCESS/FAILED
    @NotBlank
    private String orderNumber;
}