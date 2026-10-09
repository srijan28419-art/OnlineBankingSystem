package com.example.banking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
@Service public class BankService {
 private final UserRepository users; private final TransactionRepository transactions;
 BankService(UserRepository users,TransactionRepository transactions){this.users=users;this.transactions=transactions;}
 public User current(String username){return users.findByUsername(username).orElseThrow();}
 public List<BankTransaction> history(User user){return transactions.findByFromAccountOrToAccountOrderByCreatedAtDesc(user.accountNumber,user.accountNumber);}
 @Transactional public void transfer(String username,String destination,BigDecimal amount,String description){
 if(amount==null||amount.signum()<=0||amount.scale()>2)throw new IllegalArgumentException("Enter a valid amount greater than zero (maximum two decimals).");
 User sender=users.findByUsername(username).orElseThrow();User receiver=users.findByAccountNumber(destination.trim()).orElseThrow(()->new IllegalArgumentException("Recipient account not found."));
 if(sender.id.equals(receiver.id))throw new IllegalArgumentException("You cannot transfer to your own account.");
 // Acquire row locks in stable ID order to avoid deadlocks during simultaneous transfers.
 User first=users.lockById(Math.min(sender.id,receiver.id)).orElseThrow();User second=users.lockById(Math.max(sender.id,receiver.id)).orElseThrow();
 sender=sender.id.equals(first.id)?first:second;receiver=receiver.id.equals(first.id)?first:second;
 if(sender.balance.compareTo(amount)<0)throw new IllegalArgumentException("Insufficient funds.");
 sender.balance=sender.balance.subtract(amount);receiver.balance=receiver.balance.add(amount);
 transactions.save(new BankTransaction(sender.accountNumber,receiver.accountNumber,amount,description==null?"Transfer":description.trim().substring(0,Math.min(description.trim().length(),120))));
 }
}
