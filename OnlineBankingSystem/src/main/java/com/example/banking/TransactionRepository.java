package com.example.banking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TransactionRepository extends JpaRepository<BankTransaction,Long>{List<BankTransaction> findByFromAccountOrToAccountOrderByCreatedAtDesc(String from,String to);}
