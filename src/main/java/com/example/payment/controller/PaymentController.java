package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.model.PaymentRequest;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
	
    //AutoWiring dependency	
    //@Autowired   
    //private  PaymentService paymentService;
	
	//Constructor Injection
    private final PaymentService paymentService;
    
    //public PaymentController(PaymentService paymentService) {
    //    this.paymentService = paymentService;
    //}
    
    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
		return new ResponseEntity<>(
				paymentService.doPayment(paymentRequest),
				HttpStatus.OK
				);
    	
    }
}
