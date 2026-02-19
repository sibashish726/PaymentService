package com.example.payment.service;

import com.example.payment.model.PaymentRequest;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

}
