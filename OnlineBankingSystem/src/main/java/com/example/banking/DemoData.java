package com.example.banking;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.math.BigDecimal;
@Configuration public class DemoData {
 @Bean CommandLineRunner seed(UserRepository users,PasswordEncoder encoder){return args->{if(users.count()==0){users.save(new User("alice",encoder.encode("Demo@123"),"Alice Sharma","1000000001",new BigDecimal("25000.00")));users.save(new User("bob",encoder.encode("Demo@123"),"Bob Thapa","1000000002",new BigDecimal("18000.00")));users.save(new User("charlie",encoder.encode("Demo@123"),"Charlie Rai","1000000003",new BigDecimal("12000.00")));}};}
}
