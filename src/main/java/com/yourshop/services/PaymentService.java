package com.yourshop.services;

import com.yourshop.dto.payment.PaymentResponse;
import com.yourshop.dto.payment.PaymentWebhookRequest;

public interface PaymentService {
    PaymentResponse handleWebhook(PaymentWebhookRequest request);
}