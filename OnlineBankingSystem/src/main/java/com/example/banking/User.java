package com.example.banking;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="bank_users") public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(unique=true,nullable=false) public String username;
 @Column(nullable=false) public String password;
 @Column(nullable=false) public String fullName;
 @Column(unique=true,nullable=false) public String accountNumber;
 @Column(nullable=false,precision=19,scale=2) public BigDecimal balance=BigDecimal.ZERO;
 @Version public long version;
 protected User(){} public User(String u,String p,String name,String number,BigDecimal balance){this.username=u;this.password=p;this.fullName=name;this.accountNumber=number;this.balance=balance;}
}
