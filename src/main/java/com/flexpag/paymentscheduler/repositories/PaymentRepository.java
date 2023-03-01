package com.flexpag.paymentscheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpag.paymentscheduler.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
