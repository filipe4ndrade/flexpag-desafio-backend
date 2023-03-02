package com.flexpag.paymentscheduler.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

@Configuration
public class TestConfig implements CommandLineRunner{

	@Autowired
	PaymentRepository paymentRepository;

	public void run(String... args) throws Exception {
		Payment p1 = new Payment(null,1000.00, Instant.parse("2023-01-23T13:00:00Z"));
		Payment p2 = new Payment(null,750.00, Instant.parse("2023-01-12T17:40:35Z"));
		Payment p3 = new Payment(null,1200.00, Instant.parse("2023-02-07T10:18:55Z"));

		paymentRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
