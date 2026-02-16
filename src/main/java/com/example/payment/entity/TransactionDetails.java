package com.example.payment.entity;

import java.time.Instant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRANSACTION_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetails {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) 
  private Long id;
  
  @Column(name="ORDER_ID", nullable = false)
  private Long orderId;
  
  @Column(name="PAYMENT_MODE")
  private String paymentMode;
  
  @Column(name="REFERENCE_NUMBER")
  private String referenceNumber;
  
  @Column(name="PAYMENT_DATE")
  private Instant paymentDate; // Renamed for clarity
  
  @Column(name="PAYMENT_STATUS")
  private String paymentStatus;
  
  @Column(name="PAYMENT_AMOUNT")
  private Long amount;
}