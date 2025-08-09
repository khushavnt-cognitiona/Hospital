package com.yourshop.dto.payment;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long id;
    private String provider;
    private String status;
    private String transactionId;
    private Long orderId;
}