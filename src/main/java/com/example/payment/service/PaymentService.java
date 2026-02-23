package com.example.payment.service;

import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResponse;

public interface PaymentService {

	long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrder(long orderId);


}
