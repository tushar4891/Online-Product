package com.user.userservice.Repository;
import com.user.userservice.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    @Query("Select p from Payment p Join User u on u.payment = p.id where p.id = ?1 ")
    public Payment getPayment(int id);
}
