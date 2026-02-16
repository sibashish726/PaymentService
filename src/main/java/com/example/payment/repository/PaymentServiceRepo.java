package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.entity.TransactionDetails;

@Repository
public interface PaymentServiceRepo extends JpaRepository<TransactionDetails, Long>{

}
