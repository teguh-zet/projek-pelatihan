package com.arsgadget.aisyah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsgadget.aisyah.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
