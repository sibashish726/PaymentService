package com.example.payment.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.payment.entity.TransactionDetails;
import com.example.payment.exception.CustomException;
import com.example.payment.model.PaymentMode;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResponse;
import com.example.payment.repository.PaymentServiceRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{

	
	private final PaymentServiceRepo paymentServiceRepo;
	
	@Override
	public long doPayment(PaymentRequest paymentRequest) {
		log.info("Payment details : "+paymentRequest );
		TransactionDetails transactionDetails = TransactionDetails.builder()
				          .paymentDate(Instant.now())
				          .paymentMode(paymentRequest.getPaymentmode().name())
				          .paymentStatus("SUCCESS")
				          .orderId(paymentRequest.getOrderId())
				          .referenceNumber(paymentRequest.getReferenceNumber())
				          .amount(paymentRequest.getAmount())
				          .build();
		paymentServiceRepo.save(transactionDetails);
		log.info("Payment successful : "+ transactionDetails.getId());
		return transactionDetails.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrder(long orderId) {
		log.info("Payment details for order id : "+orderId);
		TransactionDetails transactionDetails = paymentServiceRepo.findByOrderId(orderId);
	    
	    // Manual null check instead of .orElseThrow()
	    if (transactionDetails == null) {
	        throw new CustomException(
	            "Payment details not found for Order ID: " + orderId, 
	            "PAYMENT_NOT_FOUND", 
	            404
	        );
	    }
		PaymentResponse paymentResponse= PaymentResponse.builder()
				                                        .paymentId(transactionDetails.getId())
				                                        .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				                                        .paymentDate(transactionDetails.getPaymentDate())
				                                        .orderId(transactionDetails.getOrderId())
				                                        .status(transactionDetails.getPaymentStatus())
				                                        .amount(transactionDetails.getAmount())
				                                        .build();
		return paymentResponse;
	}

}
