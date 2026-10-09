package com.example.banking;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity public class BankTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String fromAccount;
 @Column(nullable=false) public String toAccount;
 @Column(nullable=false,precision=19,scale=2) public BigDecimal amount;
 @Column(nullable=false) public LocalDateTime createdAt;
 public String description;
 protected BankTransaction(){} public BankTransaction(String from,String to,BigDecimal amount,String description){this.fromAccount=from;this.toAccount=to;this.amount=amount;this.description=description;this.createdAt=LocalDateTime.now();}
}
