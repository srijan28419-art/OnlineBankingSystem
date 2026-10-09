package com.example.banking;
import org.springframework.data.jpa.repository.*;
import jakarta.persistence.LockModeType;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User,Long>{Optional<User> findByUsername(String username);Optional<User> findByAccountNumber(String accountNumber);@Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select u from User u where u.id = :id") Optional<User> lockById(Long id);}
